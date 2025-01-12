package toughdevschool.ates.accounting.domain.transaction.transfer

import arrow.core.raise.either
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.billingCycle.business.BillingCycleService
import toughdevschool.ates.accounting.domain.transaction.data.Transaction
import toughdevschool.ates.accounting.domain.transaction.data.TransactionRepository
import toughdevschool.ates.accounting.event.producer.AccountingBusinessEventProducer
import toughdevschool.ates.event.business.transaction.v1.TransactionCompleted
import java.math.BigDecimal
import java.time.OffsetDateTime

@Service
class Service(
    private val billingCycleService: BillingCycleService,
    private val transactionRepository: TransactionRepository,
    private val businessEventProducer: AccountingBusinessEventProducer,
) : TransferService {

    private val secureRandom = RandomStringUtils.secure()

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    override suspend fun perform(request: TransferRequest) = either {
        val billingCycle = billingCycleService.getActive().bind()
        val createdAt = OffsetDateTime.now()

        val creditTx = Transaction(
            publicId = generatePublicId(),
            type = Transaction.Type.Transfer,
            accountId = request.source.id!!,
            billingCycleId = billingCycle.id!!,
            description = request.description,
            debit = BigDecimal.ZERO,
            credit = request.amount,
            linkedTransactionId = null,
            createdAt = createdAt,
        ).let { transactionRepository.save(it) }
        val debitTx = Transaction(
            publicId = generatePublicId(),
            type = Transaction.Type.Transfer,
            accountId = request.destination.id!!,
            billingCycleId = billingCycle.id!!,
            description = request.description,
            debit = request.amount,
            credit = BigDecimal.ZERO,
            linkedTransactionId = creditTx.id!!,
            createdAt = createdAt,
        ).let { transactionRepository.save(it) }

        sendTxCompleted(creditTx, request.source)
        sendTxCompleted(debitTx, request.destination)

        TransferResponse(
            listOf(creditTx, debitTx),
            createdAt,
        )
    }

    private fun generatePublicId(): String = secureRandom.nextAlphanumeric(PUBLIC_ID_LENGTH)

    private suspend fun sendTxCompleted(tx: Transaction, account: Account) =
        businessEventProducer.sendTransactionCompletedV1(
            TransactionCompleted(
                publicId = tx.publicId,
                type = tx.type.name,
                accountUuid = account.uuid,
                description = tx.description,
                debit = tx.debit,
                credit = tx.credit,
                createdAt = tx.createdAt,
            )
        )

    companion object {

        const val PUBLIC_ID_LENGTH = 16
    }
}
