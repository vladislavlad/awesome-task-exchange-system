package toughdevschool.ates.analytics.domain.task.crud.business

import toughdevschool.ates.analytics.domain.task.crud.data.Task
import toughdevschool.ates.analytics.domain.user.data.User
import java.util.UUID

data class TaskCreate(
    val uuid: UUID,
    val title: String,
    val user: User? = null,
)

data class TaskUpdate(
    val task: Task,
    val title: String? = null,
    val user: User? = null,
)
