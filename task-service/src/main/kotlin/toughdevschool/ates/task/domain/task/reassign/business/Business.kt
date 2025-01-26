package toughdevschool.ates.task.domain.task.reassign.business

import software.darkmatter.platform.api.http.HttpApi
import software.darkmatter.platform.service.Service
import toughdevschool.ates.task.domain.user.data.User

typealias TaskReassignApi = HttpApi<Unit>

interface TaskReassignService : Service<TasksReassignRequest, TasksReassigned>

data class TasksReassignRequest(
    val requestedBy: User,
)

data class TasksReassigned(
    val reassignedTasksCount: Int
)
