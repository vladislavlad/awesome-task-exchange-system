package toughdevschool.ates.event.business.user.v1

import software.darkmatter.platform.event.KeyAware
import java.util.UUID

data class UserRolesChanged(
    val id: UUID,
    val userUuid: UUID,
    val roles: List<String>,
) : KeyAware {

    override val key = userUuid.toString()
}
