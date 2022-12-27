package toughdevschool.ates.analytics.domain.userRole.handler

import arrow.core.flatMap
import org.springframework.stereotype.Component
import toughdevschool.ates.analytics.domain.user.business.UserService
import toughdevschool.ates.analytics.domain.userRole.business.DeleteUserRolesService
import toughdevschool.ates.analytics.domain.userRole.business.UserRoleDelete
import toughdevschool.ates.event.cud.userRole.v1.UserRoleData

@Component
class UserRoleDeleteHandler(
    private val userService: UserService,
    private val deleteUserRolesService: DeleteUserRolesService,
) {

    suspend fun handle(data: UserRoleData) =
        userService.getByUuid(data.userUuid)
            .flatMap {
                deleteUserRolesService.perform(UserRoleDelete(it, listOf(data.role)))
            }
}
