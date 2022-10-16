package toughdevschool.ates.task.domain.task.crud.business

import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.service.CrudService
import toughdevschool.ates.task.domain.task.data.Task
import java.util.UUID

interface TaskService : CrudService<Task, Long, TaskCreate, TaskUpdate> {

    suspend fun getByUuid(uuid: UUID): Either<BusinessError, Task>

    suspend fun getFlowWithStatus(status: Task.Status): Either<BusinessError, Flow<Task>>
}
