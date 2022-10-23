package toughdevschool.ates.accounting.domain.task.crud.business

import toughdevschool.ates.accounting.domain.task.crud.data.Task
import toughdevschool.ates.accounting.domain.user.data.User
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
