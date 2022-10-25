package toughdevschool.ates.event.business.task.v1

import toughdevschool.ates.event.KeyAware
import java.util.UUID

data class TaskCompleted(
    val taskUuid: UUID,
    val userUuid: UUID,
) : KeyAware {

    override val key = taskUuid.toString()
}

