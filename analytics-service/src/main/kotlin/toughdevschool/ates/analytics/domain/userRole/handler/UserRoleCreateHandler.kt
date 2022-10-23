package toughdevschool.ates.analytics.domain.userRole.handler

import arrow.core.flatMap
import org.springframework.stereotype.Component
import toughdevschool.ates.analytics.domain.user.business.UserService
import toughdevschool.ates.analytics.domain.userRole.business.AddUserRolesService
import toughdevschool.ates.analytics.domain.userRole.business.UserRoleAdd
import toughdevschool.ates.event.cud.userRole.v1.UserRoleData

@Component
class UserRoleCreateHandler(
    private val userService: UserService,
    private val addUserRolesService: AddUserRolesService,
) {

    suspend fun handle(data: UserRoleData) =
        userService.getByUuid(data.userUuid)
            .flatMap {
                addUserRolesService.perform(UserRoleAdd(it, data.roles))
            }
}
