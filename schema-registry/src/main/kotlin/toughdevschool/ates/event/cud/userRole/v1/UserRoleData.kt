package toughdevschool.ates.event.cud.userRole.v1

import toughdevschool.ates.event.KeyAware
import java.util.UUID

data class UserRoleData(
    val userUuid: UUID,
    val roles: List<String> = emptyList(),
) : KeyAware {

    override val key = userUuid.toString()
}