package toughdevschool.ates.task.domain.task.reassign.business

import software.darkmatter.platform.api.http.HttpApi
import software.darkmatter.platform.service.Service

typealias TaskReassignApi = HttpApi<Unit>

interface TaskReassignService : Service<Unit, TasksReassigned>

data class TasksReassigned(
    val reassignedTasksCount: Int
)
