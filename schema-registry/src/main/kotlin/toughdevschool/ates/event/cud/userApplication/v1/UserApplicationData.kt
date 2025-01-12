package toughdevschool.ates.event.cud.userApplication.v1

import software.darkmatter.platform.event.KeyAware
import java.util.UUID

data class UserApplicationData(
    val userUuid: UUID,
    val application: String,
) : KeyAware {

    override fun eventKey(): String = userUuid.toString()
}
