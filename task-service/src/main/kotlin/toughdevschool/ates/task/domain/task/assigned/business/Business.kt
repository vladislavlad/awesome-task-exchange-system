package toughdevschool.ates.task.domain.task.assigned.business

import org.springframework.data.domain.Pageable
import software.darkmatter.platform.api.http.HttpApi
import software.darkmatter.platform.service.Service
import toughdevschool.ates.task.api.TaskAssignedListDto
import toughdevschool.ates.task.domain.task.data.Task
import java.util.UUID

typealias TaskAssignedListApi = HttpApi<TaskAssignedListDto.Request>

typealias TaskAssignedService = Service<TaskAssignedListRequest, TaskAssignedListResponse>

data class TaskAssignedListRequest(
    val userUuid: UUID,
    val pageable: Pageable
)

data class TaskAssignedListResponse(
    val tasks: List<Task>
)
