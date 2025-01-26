package toughdevschool.ates.analytics.domain.task.crud.business

import toughdevschool.ates.analytics.domain.task.crud.data.Task
import toughdevschool.ates.analytics.domain.user.data.User
import java.time.OffsetDateTime
import java.util.UUID

data class TaskCreate(
    val uuid: UUID,
    val user: User? = null,
    val title: String,
    val status: Task.Status,
    val createdAt: OffsetDateTime,
    val completedAt: OffsetDateTime? = null,
)

data class TaskUpdate(
    val task: Task,
    val user: User? = null,
    val title: String? = null,
    val status: Task.Status,
    val completedAt: OffsetDateTime? = null,
)

enum class Scale {
    Day,
    Week,
    Month,
}
