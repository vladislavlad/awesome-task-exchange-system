package toughdevschool.ates.task.domain.userRole.handler

import arrow.core.flatMap
import org.springframework.stereotype.Component
import toughdevschool.ates.event.cud.userRole.v1.UserRoleData
import toughdevschool.ates.task.domain.user.business.UserService
import toughdevschool.ates.task.domain.userRole.business.AddUserRolesService
import toughdevschool.ates.task.domain.userRole.business.UserRoleAdd

@Component
class UserRoleCreateHandler(
    private val userService: UserService,
    private val addUserRolesService: AddUserRolesService,
) {

    suspend fun handle(data: UserRoleData) =
        userService.getByUuid(data.userUuid)
            .flatMap {
                addUserRolesService.perform(UserRoleAdd(it, listOf(data.role)))
            }
}
