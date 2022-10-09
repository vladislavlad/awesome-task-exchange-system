package toughdevschool.ates.interaction.events.model

import java.util.UUID

data class UserInfo(
    val uuid: UUID,
    val username: String,
    var firstName: String?,
    var lastName: String?,
    var middleName: String?,
)
