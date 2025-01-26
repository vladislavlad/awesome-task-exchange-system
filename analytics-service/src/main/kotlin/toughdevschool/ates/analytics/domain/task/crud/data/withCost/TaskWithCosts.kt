package toughdevschool.ates.analytics.domain.task.crud.data.withCost

import toughdevschool.ates.analytics.domain.task.crud.data.Task
import java.time.OffsetDateTime
import java.util.UUID

data class TaskWithCosts(
    val taskUuid: UUID,
    var userUuid: UUID?,
    var title: String,
    var status: Task.Status,
    val assignCost: Int,
    val reward: Int,
    val createdAt: OffsetDateTime,
    var completedAt: OffsetDateTime?,
)
