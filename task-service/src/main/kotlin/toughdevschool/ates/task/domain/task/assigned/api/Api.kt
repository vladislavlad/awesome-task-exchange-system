package toughdevschool.ates.task.domain.task.assigned.api

import org.springframework.stereotype.Component
import software.darkmatter.platform.api.http.ServiceApi
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.task.api.TaskAssignedListDto
import toughdevschool.ates.task.domain.task.assigned.business.TaskAssignedListApi
import toughdevschool.ates.task.domain.task.assigned.business.TaskAssignedListRequest
import toughdevschool.ates.task.domain.task.assigned.business.TaskAssignedListResponse
import toughdevschool.ates.task.domain.task.assigned.business.TaskAssignedService

@Component
class Api(
    override val requestAssembler: RequestAssembler<TaskAssignedListDto.Request, TaskAssignedListRequest>,
    override val service: TaskAssignedService,
    override val responseAssembler: ResponseAssembler<TaskAssignedListResponse, TaskAssignedListDto.Response>,
) : ServiceApi<TaskAssignedListDto.Request, TaskAssignedListDto.Response, TaskAssignedListRequest, TaskAssignedListResponse>(),
    TaskAssignedListApi
