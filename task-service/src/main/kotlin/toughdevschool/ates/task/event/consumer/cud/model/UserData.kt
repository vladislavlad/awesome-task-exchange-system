package toughdevschool.ates.task.event.consumer.cud.model

import java.util.UUID

data class UserData(
    val uuid: UUID,
    val username: String,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
)
