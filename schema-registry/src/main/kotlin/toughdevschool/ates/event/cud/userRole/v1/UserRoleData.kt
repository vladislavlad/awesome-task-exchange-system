package toughdevschool.ates.event.cud.userRole.v1

import software.darkmatter.platform.event.KeyAware
import java.util.UUID

data class UserRoleData(
    val userUuid: UUID,
    val role: String,
) : KeyAware {

    override val key = userUuid.toString()
}
