package toughdevschool.ates.event.cud.user.v1

import software.darkmatter.platform.event.KeyAware
import java.util.UUID

data class UserData(
    val uuid: UUID,
    val username: String,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
) : KeyAware {

    override fun eventKey(): String = uuid.toString()
}
