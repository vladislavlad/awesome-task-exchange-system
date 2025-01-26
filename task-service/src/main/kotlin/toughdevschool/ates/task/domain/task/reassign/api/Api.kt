package toughdevschool.ates.task.domain.task.reassign.api

import org.springframework.stereotype.Component
import software.darkmatter.platform.api.http.ServiceApi
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.reassign.business.TaskReassignApi
import toughdevschool.ates.task.domain.task.reassign.business.TaskReassignService
import toughdevschool.ates.task.domain.task.reassign.business.TasksReassignRequest
import toughdevschool.ates.task.domain.task.reassign.business.TasksReassigned

@Component
class Api(
    override val requestAssembler: RequestAssembler<Unit, TasksReassignRequest>,
    override val service: TaskReassignService,
    override val responseAssembler: ResponseAssembler<TasksReassigned, TaskDto.TasksReassignedResponse>,
) : ServiceApi<Unit, TaskDto.TasksReassignedResponse, TasksReassignRequest, TasksReassigned>(),
    TaskReassignApi
