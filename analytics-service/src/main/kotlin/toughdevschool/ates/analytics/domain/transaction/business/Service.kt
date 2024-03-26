package toughdevschool.ates.analytics.domain.transaction.business

import org.springframework.stereotype.Service
import software.darkmatter.platform.business.BusinessChecks
import software.darkmatter.platform.business.businessChecks
import software.darkmatter.platform.security.service.AuthCrudService
import toughdevschool.ates.analytics.domain.transaction.data.Transaction
import toughdevschool.ates.analytics.domain.transaction.data.TransactionPagingRepository
import toughdevschool.ates.analytics.domain.transaction.data.TransactionRepository
import java.time.OffsetDateTime

@Service
class Service(
    repository: TransactionRepository,
    pagingRepository: TransactionPagingRepository,
) : AuthCrudService<Transaction, Long, TransactionCreate, Unit>(repository, pagingRepository),
    TransactionService {

    override suspend fun createEntity(businessCreate: TransactionCreate) =
        Transaction(
            publicId = businessCreate.publicId,
            type = businessCreate.type,
            accountUuid = businessCreate.accountUuid,
            debit = businessCreate.debit,
            credit = businessCreate.credit,
            completedAt = businessCreate.completedAt,
            createdAt = OffsetDateTime.now(),
        )

    override val checksOnCreate: BusinessChecks<TransactionCreate> = businessChecks()

    override suspend fun updateEntity(businessUpdate: Unit): Transaction = throw UnsupportedOperationException()

    override val checksOnUpdate: BusinessChecks<Unit> = businessChecks()

    override fun entityClass() = Transaction::class
}
