package toughdevschool.ates.event.cud.task.v1

import software.darkmatter.platform.event.KeyAware
import java.util.UUID

data class TaskData(
    val uuid: UUID,
    val title: String,
    val description: String,
    var status: String,
    var userUuid: UUID,
) : KeyAware {

    override fun eventKey(): String = uuid.toString()
}
