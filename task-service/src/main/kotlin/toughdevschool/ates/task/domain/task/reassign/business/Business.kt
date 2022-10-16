package toughdevschool.ates.task.domain.task.reassign.business

import software.darkmatter.platform.api.http.ServiceApi
import software.darkmatter.platform.service.Service
import toughdevschool.ates.task.api.TaskDto

typealias TaskReassignApi = ServiceApi<Unit, TaskDto.TasksReassignedResponse, Unit, TasksReassigned>

interface TaskReassignService : Service<Unit, TasksReassigned>

data class TasksReassigned(
    val reassignedTasksCount: Int
)
