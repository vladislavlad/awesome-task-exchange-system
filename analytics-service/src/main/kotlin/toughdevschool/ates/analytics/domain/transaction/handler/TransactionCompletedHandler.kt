package toughdevschool.ates.analytics.domain.transaction.handler

import arrow.core.raise.either
import io.micrometer.observation.ObservationRegistry
import org.springframework.stereotype.Component
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.observation.Observable
import toughdevschool.ates.analytics.domain.transaction.business.TransactionCreate
import toughdevschool.ates.analytics.domain.transaction.business.TransactionService
import toughdevschool.ates.analytics.domain.transaction.data.Transaction
import toughdevschool.ates.event.business.transaction.v1.TransactionCompleted

@Component
class TransactionCompletedHandler(
    override val observationRegistry: ObservationRegistry,
    private val transactionService: TransactionService,
) : Observable {

    suspend fun handle(transactionCompleted: TransactionCompleted) =
        either<BusinessError, Unit> {
            observed("transaction-completed-handler") {
                transactionService.create(
                    TransactionCreate(
                        publicId = transactionCompleted.publicId,
                        type = Transaction.Type.valueOf(transactionCompleted.type),
                        accountUuid = transactionCompleted.accountUuid,
                        debit = transactionCompleted.debit,
                        credit = transactionCompleted.credit,
                        completedAt = transactionCompleted.createdAt,
                    )
                )
            }
        }
}
