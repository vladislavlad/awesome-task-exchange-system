package toughdevschool.ates.accounting.domain.transaction.transfer

import arrow.core.continuations.either
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import toughdevschool.ates.accounting.domain.billingCycle.business.BillingCycleService
import toughdevschool.ates.accounting.domain.transaction.data.Transaction
import toughdevschool.ates.accounting.domain.transaction.data.TransactionRepository
import java.math.BigDecimal
import java.time.OffsetDateTime

@Service
class Service(
    private val billingCycleService: BillingCycleService,
    private val transactionRepository: TransactionRepository,
) : TransferService {

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    override suspend fun perform(request: TransferRequest) = either {
        val billingCycle = billingCycleService.getActive().bind()
        val createdAt = OffsetDateTime.now()

        val creditTx = Transaction(
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
            type = Transaction.Type.Transfer,
            accountId = request.destination.id!!,
            billingCycleId = billingCycle.id!!,
            description = request.description,
            debit = request.amount,
            credit = BigDecimal.ZERO,
            linkedTransactionId = creditTx.id!!,
            createdAt = createdAt,
        ).let { transactionRepository.save(it) }

        TransferResponse(
            listOf(creditTx, debitTx),
            createdAt,
        )
    }
}
