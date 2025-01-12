package toughdevschool.ates.event.business.user.v1

import software.darkmatter.platform.event.KeyAware
import java.util.UUID

data class UserRolesChanged(
    val userUuid: UUID,
    val roles: List<String>,
) : KeyAware {

    override fun eventKey(): String = userUuid.toString()
}
