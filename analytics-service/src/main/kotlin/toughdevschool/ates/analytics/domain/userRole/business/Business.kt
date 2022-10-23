package toughdevschool.ates.analytics.domain.userRole.business

import software.darkmatter.platform.service.Service
import toughdevschool.ates.analytics.domain.user.data.User

typealias AddUserRolesService = Service<UserRoleAdd, Unit>
typealias DeleteUserRolesService = Service<UserRoleDelete, Unit>

data class UserRoleAdd(
    val user: User,
    val roles: List<String>
)

data class UserRoleDelete(
    val user: User,
    val roles: List<String>
)
