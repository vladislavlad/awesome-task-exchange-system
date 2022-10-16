package toughdevschool.ates.task.domain.task.reassign.api

import arrow.core.right
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.reassign.business.TasksReassigned

@Component
class ResponseAssembler : ResponseAssembler<TasksReassigned, TaskDto.TasksReassignedResponse> {

    override suspend fun assemble(business: TasksReassigned) =
        TaskDto.TasksReassignedResponse(business.reassignedTasksCount).right()
}
