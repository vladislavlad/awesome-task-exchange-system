package toughdevschool.ates.event.business.task.v1

import software.darkmatter.platform.event.KeyAware
import java.util.UUID

data class TaskAssigned(
    val taskUuid: UUID,
    val userUuid: UUID,
) : KeyAware {

    override fun eventKey(): String = taskUuid.toString()
}
