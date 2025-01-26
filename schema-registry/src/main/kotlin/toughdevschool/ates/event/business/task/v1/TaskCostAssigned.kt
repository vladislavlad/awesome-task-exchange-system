package toughdevschool.ates.event.business.task.v1

import software.darkmatter.platform.event.KeyAware
import java.util.UUID

data class TaskCostAssigned(
    val taskUuid: UUID,
    val assignCost: Int,
    val reward: Int,
) : KeyAware {

    override fun eventKey(): String = taskUuid.toString()
}

