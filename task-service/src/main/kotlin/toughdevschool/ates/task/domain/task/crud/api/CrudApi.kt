package toughdevschool.ates.task.domain.task.crud.api

import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.crud.business.TaskCreate
import toughdevschool.ates.task.domain.task.crud.business.TaskCrudApi
import toughdevschool.ates.task.domain.task.crud.business.TaskService
import toughdevschool.ates.task.domain.task.crud.business.TaskUpdate
import toughdevschool.ates.task.domain.task.data.Task

@Component
class CrudApi(
    override val service: TaskService,
    override val responseAssembler: ResponseAssembler<Task, TaskDto.Response>,
    override val createRequestAssembler: RequestAssembler<TaskDto.CreateRequest, TaskCreate>,
    override val updateRequestAssembler: RequestAssembler<TaskDto.UpdateRequest, TaskUpdate>,
) : TaskCrudApi() {

    override suspend fun delete(id: Long) = TODO()
}
