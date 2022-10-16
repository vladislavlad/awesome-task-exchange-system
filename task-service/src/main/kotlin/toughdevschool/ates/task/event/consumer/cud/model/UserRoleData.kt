package toughdevschool.ates.task.event.consumer.cud.model

import java.util.UUID

data class UserRoleData(
    val userUuid: UUID,
    val roles: List<String> = emptyList(),
)
