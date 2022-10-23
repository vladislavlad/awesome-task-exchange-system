package toughdevschool.ates.accounting.domain.transaction.transition

import arrow.core.continuations.either
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.accounting.domain.billingCycle.business.BillingCycleService
import toughdevschool.ates.accounting.domain.transaction.data.Transaction
import toughdevschool.ates.accounting.domain.transaction.data.TransactionRepository
import java.math.BigDecimal
import java.time.OffsetDateTime

@Service
class Service(
    private val transactionRepository: TransactionRepository,
    private val billingCycleService: BillingCycleService,
) : TransitionService {

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    override suspend fun perform(request: TransitionRequest) = either<BusinessError, TransitionResponse> {
        val billingCycle = billingCycleService.getActive().bind()
        val creditTx = Transaction(
            type = Transaction.Type.Transition,
            accountId = request.source.id!!,
            billingCycleId = billingCycle.id!!,
            description = request.description,
            debit = BigDecimal.ZERO,
            credit = request.amount,
            linkedTransactionId = null,
            createdAt = OffsetDateTime.now(),
        ).let { transactionRepository.save(it) }
        val debitTx = Transaction(
            type = Transaction.Type.Transition,
            accountId = request.source.id!!,
            billingCycleId = billingCycle.id!!,
            description = request.description,
            debit = request.amount,
            credit = BigDecimal.ZERO,
            linkedTransactionId = creditTx.id!!,
            createdAt = OffsetDateTime.now(),
        ).let { transactionRepository.save(it) }

        TransitionResponse(
            listOf(creditTx, debitTx)
        )
    }
}
