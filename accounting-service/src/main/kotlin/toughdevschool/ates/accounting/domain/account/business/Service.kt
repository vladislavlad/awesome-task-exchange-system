package toughdevschool.ates.accounting.domain.account.business

import org.springframework.stereotype.Service
import software.darkmatter.platform.business.BusinessCheck
import software.darkmatter.platform.business.BusinessChecks
import software.darkmatter.platform.business.businessChecks
import software.darkmatter.platform.business.onRight
import software.darkmatter.platform.data.PagingRepository
import software.darkmatter.platform.error.uniqueConstraintConflict
import software.darkmatter.platform.service.AbstractCrudService
import software.darkmatter.platform.syntax.leftIfNull
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.account.data.AccountRepository
import toughdevschool.ates.accounting.domain.user.data.User
import java.util.UUID

@Service
class Service(
    private val repository: AccountRepository,
    pagingRepository: PagingRepository<Account, Long>,
) : AbstractCrudService<Account, Long, AccountCreate, AccountUpdate>(repository, pagingRepository),
    AccountService {

    override suspend fun getByUuid(uuid: UUID) = repository.findByUuid(uuid).leftIfNull { notFound }

    override suspend fun getByUser(user: User) = repository.findByUserId(user.id!!).leftIfNull { notFound }

    override suspend fun createEntity(businessCreate: AccountCreate) =
        Account(
            uuid = UUID.randomUUID(),
            type = businessCreate.type,
            description = businessCreate.description,
            status = Account.Status.Active,
            userId = businessCreate.user.id!!,
        )

    override suspend fun updateEntity(businessUpdate: AccountUpdate): Account =
        with(businessUpdate.account) {
            status = businessUpdate.status
            businessUpdate.description?.also { description = it }
            this
        }

    override val checksOnCreate: BusinessChecks<AccountCreate> = businessChecks(
        onRight(
            check = ::getByUser,
            accessor = AccountCreate::user,
            error = { uniqueConstraintConflict("Account already created for that User") }
        )
    )
    override val checksOnUpdate = emptyList<BusinessCheck<AccountUpdate>>()

    override fun entityClass() = Account::class
}
