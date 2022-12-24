package toughdevschool.ates.accounting.domain.user.business

import org.springframework.stereotype.Service
import software.darkmatter.platform.business.BusinessCheck
import software.darkmatter.platform.business.businessChecks
import software.darkmatter.platform.business.onRight
import software.darkmatter.platform.data.PagingRepository
import software.darkmatter.platform.error.uniqueConstraintConflict
import software.darkmatter.platform.security.service.AuthCrudService
import software.darkmatter.platform.syntax.leftIfNull
import toughdevschool.ates.accounting.domain.user.data.User
import toughdevschool.ates.accounting.domain.user.data.UserRepository
import java.util.UUID

@Service
class Service(
    private val repository: UserRepository,
    pagingRepository: PagingRepository<User, Long>,
    accountExtension: AccountExtension,
) : AuthCrudService<User, Long, UserCreate, UserUpdate>(repository, pagingRepository, accountExtension),
    UserService {

    override suspend fun getByUsername(username: String) = repository.findByUsername(username).leftIfNull { notFound }

    override suspend fun getByUuid(uuid: UUID) = repository.findByUuid(uuid).leftIfNull { notFound }

    override suspend fun createEntity(businessCreate: UserCreate) =
        User(
            uuid = businessCreate.uuid,
            username = businessCreate.username,
            firstName = businessCreate.firstName,
            lastName = businessCreate.lastName,
            middleName = businessCreate.middleName,
        )

    override suspend fun updateEntity(businessUpdate: UserUpdate): User =
        with(businessUpdate.user) {
            firstName = businessUpdate.firstName
            lastName = businessUpdate.lastName
            middleName = businessUpdate.middleName
            this
        }

    override val checksOnCreate = businessChecks(
        onRight(
            check = ::getByUsername,
            accessor = UserCreate::username,
            error = { uniqueConstraintConflict("Username is already used") }
        )
    )

    override val checksOnUpdate = emptyList<BusinessCheck<UserUpdate>>()

    override fun entityClass() = User::class
}
