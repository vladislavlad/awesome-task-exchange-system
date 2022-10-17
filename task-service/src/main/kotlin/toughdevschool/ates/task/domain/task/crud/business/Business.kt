package toughdevschool.ates.task.domain.task.crud.business

import software.darkmatter.platform.api.http.ServiceCrudApi
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.data.Task
import toughdevschool.ates.task.domain.user.data.User

typealias TaskCrudApi = ServiceCrudApi<Long,
    TaskDto.Response, TaskDto.CreateRequest, TaskDto.UpdateRequest,
    Task, TaskCreate, TaskUpdate>

data class TaskCreate(
    val title: String,
    val description: String,
    val jiraId: String?,
    val user: User,
)

data class TaskUpdate(
    val task: Task,
    val title: String? = null,
    val description: String? = null,
    val jiraId: String? = null,
    val status: Task.Status? = null,
    val user: User? = null,
)
