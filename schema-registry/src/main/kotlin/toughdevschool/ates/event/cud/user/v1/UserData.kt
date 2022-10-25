package toughdevschool.ates.event.cud.user.v1

import toughdevschool.ates.event.KeyAware
import java.util.UUID

data class UserData(
    val uuid: UUID,
    val username: String,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
) : KeyAware {

    override val key = uuid.toString()
}
