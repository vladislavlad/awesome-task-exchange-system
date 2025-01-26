package toughdevschool.ates.analytics.domain.task.cost.business

import arrow.core.Either
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.service.CrudService
import toughdevschool.ates.analytics.domain.task.cost.data.TaskCost
import java.util.UUID

interface TaskCostService : CrudService<TaskCost, Long, TaskCostCreate, TaskCostUpdate> {

    suspend fun getByTaskUuid(taskUuid: UUID): Either<BusinessError, TaskCost>
}
