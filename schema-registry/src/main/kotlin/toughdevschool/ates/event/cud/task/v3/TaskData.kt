package toughdevschool.ates.event.cud.task.v3

import software.darkmatter.platform.event.KeyAware
import java.time.OffsetDateTime
import java.util.UUID

data class TaskData(
    val uuid: UUID,
    var userUuid: UUID,
    val title: String,
    val description: String,
    var status: String,
    val jiraId: String?,
    val createdAt: OffsetDateTime,
    val completedAt: OffsetDateTime?,
) : KeyAware {

    override fun eventKey(): String = uuid.toString()
}
