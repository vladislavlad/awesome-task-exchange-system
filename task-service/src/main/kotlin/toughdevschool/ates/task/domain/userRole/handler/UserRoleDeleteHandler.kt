package toughdevschool.ates.task.domain.userRole.handler

import arrow.core.flatMap
import org.springframework.stereotype.Component
import toughdevschool.ates.event.cud.userRole.v1.UserRoleData
import toughdevschool.ates.task.domain.user.business.UserService
import toughdevschool.ates.task.domain.userRole.business.DeleteUserRolesService
import toughdevschool.ates.task.domain.userRole.business.UserRoleDelete

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
