package toughdevschool.ates.analytics.domain.task.crud.business

import arrow.core.Either
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.service.CrudService
import toughdevschool.ates.analytics.domain.task.crud.data.Task
import java.util.UUID

interface TaskService : CrudService<Task, Long, TaskCreate, TaskUpdate> {

    suspend fun getByUuid(uuid: UUID): Either<BusinessError, Task>
}
