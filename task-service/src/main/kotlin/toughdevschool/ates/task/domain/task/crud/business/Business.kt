package toughdevschool.ates.task.domain.task.crud.business

import software.darkmatter.platform.api.http.ServiceCrudApi
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.data.Task

typealias TaskCrudApi = ServiceCrudApi<Long,
    TaskDto.Response, TaskDto.CreateRequest, TaskDto.UpdateRequest,
    Task, TaskCreate, TaskUpdate>

data class TaskCreate(
    val title: String,
    val description: String,
    val userId: Long,
)

data class TaskUpdate(
    val task: Task,
    val title: String? = null,
    val description: String? = null,
    val status: Task.Status? = null,
    val userId: Long? = null,
)
