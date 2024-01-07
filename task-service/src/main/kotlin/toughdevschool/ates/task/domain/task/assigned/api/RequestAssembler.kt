package toughdevschool.ates.task.domain.task.assigned.api

import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.security.context.jwtAuthenticationFromSecurityContext
import toughdevschool.ates.task.api.TaskAssignedListDto
import toughdevschool.ates.task.domain.task.assigned.business.TaskAssignedListRequest

@Component
class RequestAssembler : RequestAssembler<TaskAssignedListDto.Request, TaskAssignedListRequest> {

    override suspend fun assemble(request: TaskAssignedListDto.Request) =
        jwtAuthenticationFromSecurityContext()
            .map {
                TaskAssignedListRequest(
                    userUuid = it.jwt.subject,
                    pageable = request.pageable,
                )
            }
}
