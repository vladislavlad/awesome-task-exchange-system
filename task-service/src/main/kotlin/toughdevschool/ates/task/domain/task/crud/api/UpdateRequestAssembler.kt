package toughdevschool.ates.task.domain.task.crud.api

import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.crud.business.TaskService
import toughdevschool.ates.task.domain.task.crud.business.TaskUpdate

@Component
class UpdateRequestAssembler(
    private val taskService: TaskService,
) : RequestAssembler<TaskDto.UpdateRequest, TaskUpdate> {

    override suspend fun assemble(request: TaskDto.UpdateRequest) =
        taskService.get(request.taskId)
            .map {
                TaskUpdate(
                    task = it,
                    title = request.body.title!!,
                    description = request.body.description!!,
                    status = request.body.status,
                    userId = null,
                )
            }
}
