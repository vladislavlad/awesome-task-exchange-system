package toughdevschool.ates.task.domain.task.crud.business

import software.darkmatter.platform.api.http.ServiceCrudApi
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.data.Task
import toughdevschool.ates.task.domain.user.data.User
import java.time.OffsetDateTime
import java.util.UUID

typealias TaskCrudApi = ServiceCrudApi<Long,
    TaskDto.Response, TaskDto.CreateRequest, TaskDto.UpdateRequest,
    Task, TaskCreate, TaskUpdate>

data class TaskCreate(
    val user: User,
    val title: String,
    val description: String,
    val jiraId: String?,
    val createdBy: UUID,
)

data class TaskUpdate(
    val task: Task,
    val user: User? = null,
    val title: String? = null,
    val description: String? = null,
    val jiraId: String? = null,
    val status: Task.Status? = null,
    val completedAt: OffsetDateTime? = null,
    val updatedBy: UUID,
)
