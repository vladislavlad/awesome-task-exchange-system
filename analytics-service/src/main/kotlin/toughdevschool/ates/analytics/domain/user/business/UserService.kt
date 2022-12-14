package toughdevschool.ates.analytics.domain.user.business

import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.service.CrudService
import toughdevschool.ates.analytics.domain.user.data.User
import java.util.UUID

interface UserService : CrudService<User, Long, UserCreate, UserUpdate> {

    suspend fun getByUsername(username: String): Either<BusinessError, User>

    suspend fun getByUuid(uuid: UUID): Either<BusinessError, User>

    suspend fun getFlowWithRoleIn(roles: List<String>): Either<BusinessError, Flow<User>>
}
