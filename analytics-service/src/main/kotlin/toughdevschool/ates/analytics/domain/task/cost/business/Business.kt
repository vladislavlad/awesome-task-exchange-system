package toughdevschool.ates.analytics.domain.task.cost.business

import toughdevschool.ates.analytics.domain.task.cost.data.TaskCost
import java.util.UUID

data class TaskCostCreate(
    val taskUuid: UUID,
    val assignCost: Int,
    val reward: Int,
)

data class TaskCostUpdate(
    val taskCost: TaskCost,
    val assignCost: Int,
    val reward: Int,
)
