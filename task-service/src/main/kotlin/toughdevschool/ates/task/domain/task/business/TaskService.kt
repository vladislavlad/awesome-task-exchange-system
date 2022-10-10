package toughdevschool.ates.task.domain.task.business

import arrow.core.Either
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.service.CrudService
import toughdevschool.ates.task.domain.task.data.Task
import java.util.UUID

interface TaskService : CrudService<Task, Long, TaskCreate, TaskUpdate> {

    suspend fun getByUuid(uuid: UUID): Either<BusinessError, Task>
}
