package toughdevschool.ates.task.domain.userRole.business

import arrow.core.Either
import arrow.core.right
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.task.domain.userRole.data.UserRoleRepository

@Service
class DeleteService(
    private val userRoleRepository: UserRoleRepository,
) : DeleteUserRolesService {

    @Transactional
    override suspend fun perform(request: UserRoleDelete): Either<BusinessError, Unit> =
        userRoleRepository.deleteAllByUserIdAndRoleIn(request.user.id!!, request.roles).right()
}
