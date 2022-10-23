package toughdevschool.ates.accounting.domain.user.business

import arrow.core.Either
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.service.CrudService
import toughdevschool.ates.accounting.domain.user.data.User
import java.util.UUID

interface UserService : CrudService<User, Long, UserCreate, UserUpdate> {

    suspend fun getByUsername(username: String): Either<BusinessError, User>

    suspend fun getByUuid(uuid: UUID): Either<BusinessError, User>
}
