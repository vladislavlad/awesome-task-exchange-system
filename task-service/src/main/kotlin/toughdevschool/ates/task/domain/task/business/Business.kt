package toughdevschool.ates.task.domain.task.business

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
    val title: String,
    val description: String,
    val status: Task.Status,
    val userId: Long?,
)
