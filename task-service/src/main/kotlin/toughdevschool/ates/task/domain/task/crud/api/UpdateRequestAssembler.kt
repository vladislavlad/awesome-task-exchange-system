package toughdevschool.ates.task.domain.task.crud.api

import arrow.core.raise.either
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.security.context.jwtAuthenticationFromSecurityContext
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.crud.business.TaskService
import toughdevschool.ates.task.domain.task.crud.business.TaskUpdate

@Component
class UpdateRequestAssembler(
    private val taskService: TaskService,
) : RequestAssembler<TaskDto.UpdateRequest, TaskUpdate> {

    override suspend fun assemble(request: TaskDto.UpdateRequest) = either {
        val task = taskService.get(request.taskId).bind()

        TaskUpdate(
            task = task,
            user = null,
            title = request.body.title!!,
            jiraId = request.body.jiraId,
            description = request.body.description!!,
            updatedBy = jwtAuthenticationFromSecurityContext().bind().jwt.subject
        )
    }
}
