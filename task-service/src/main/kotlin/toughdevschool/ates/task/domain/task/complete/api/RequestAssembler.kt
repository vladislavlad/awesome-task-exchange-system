package toughdevschool.ates.task.domain.task.complete.api

import arrow.core.raise.either
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.security.context.jwtAuthenticationFromSecurityContext
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.complete.business.TaskComplete
import toughdevschool.ates.task.domain.task.crud.business.TaskService
import toughdevschool.ates.task.domain.user.business.UserService

@Component
class RequestAssembler(
    private val userService: UserService,
    private val taskService: TaskService,
) : RequestAssembler<TaskDto.TaskCompleteRequest, TaskComplete> {

    override suspend fun assemble(request: TaskDto.TaskCompleteRequest) = either {
        val auth = jwtAuthenticationFromSecurityContext().bind()
        val user = userService.getByUuid(auth.jwt.subject).bind()
        val task = taskService.get(request.taskId).bind()

        TaskComplete(user, task, user.uuid)
    }
}
