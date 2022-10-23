package toughdevschool.ates.analytics.domain.userRole.business

import arrow.core.Either
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.syntax.UnitRight
import toughdevschool.ates.analytics.domain.userRole.data.UserRole
import toughdevschool.ates.analytics.domain.userRole.data.UserRoleRepository

@Service
class AddService(
    private val userRoleRepository: UserRoleRepository,
) : AddUserRolesService {

    @Transactional
    override suspend fun perform(request: UserRoleAdd): Either<BusinessError, Unit> {
        request.roles
            .map { UserRole(userId = request.user.id!!, role = it) }
            .let { userRoleRepository.saveAll(it).toList() }

        return UnitRight
    }
}
