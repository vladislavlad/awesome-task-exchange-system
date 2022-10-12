package toughdevschool.ates.task.event.consumer.cud.model

import java.util.UUID

data class UserData(
    val uuid: UUID,
    val username: String,
    var firstName: String?,
    var lastName: String?,
    var middleName: String?,
)
