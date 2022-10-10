package toughdevschool.ates.task.domain.task.assembler

import arrow.core.right
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.business.TaskCreate
import toughdevschool.ates.task.domain.user.business.UserService

@Component
class CreateRequestAssembler(
    private val userService: UserService,
) : RequestAssembler<TaskDto.CreateRequest, TaskCreate> {

    override suspend fun assemble(request: TaskDto.CreateRequest) =
        TaskCreate(
            title = request.title!!,
            description = request.description!!,
            userId = userService.list(PageRequest.of(0, 10)).first().id!! // TODO random
        ).right()
}
