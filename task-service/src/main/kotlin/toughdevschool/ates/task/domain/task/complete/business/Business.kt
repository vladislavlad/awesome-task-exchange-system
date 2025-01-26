package toughdevschool.ates.task.domain.task.complete.business

import software.darkmatter.platform.api.http.HttpApi
import software.darkmatter.platform.service.Service
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.data.Task
import toughdevschool.ates.task.domain.user.data.User
import java.util.UUID

typealias TaskCompleteApi = HttpApi<TaskDto.TaskCompleteRequest>

interface TaskCompleteService : Service<TaskComplete, Unit>

data class TaskComplete(
    val user: User,
    val task: Task,
    val updatedBy: UUID,
)
