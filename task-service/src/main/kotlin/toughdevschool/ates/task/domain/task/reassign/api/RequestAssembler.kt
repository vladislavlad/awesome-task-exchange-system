package toughdevschool.ates.task.domain.task.reassign.api

import arrow.core.raise.either
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.security.context.jwtAuthenticationFromSecurityContext
import toughdevschool.ates.task.domain.task.reassign.business.TasksReassignRequest
import toughdevschool.ates.task.domain.user.business.UserService

@Component
class RequestAssembler(
    private val userService: UserService,
) : RequestAssembler<Unit, TasksReassignRequest> {

    override suspend fun assemble(request: Unit) = either {
        val auth = jwtAuthenticationFromSecurityContext().bind()
        val user = userService.getByUuid(auth.jwt.subject).bind()

        TasksReassignRequest(user)
    }
}
