package toughdevschool.ates.event.cud.task.v2

import toughdevschool.ates.event.KeyAware
import java.util.UUID

data class TaskData(
    val uuid: UUID,
    val title: String,
    val description: String,
    var status: String,
    val jiraId: String?,
    var userUuid: UUID,
) : KeyAware {

    override val key = uuid.toString()
}
