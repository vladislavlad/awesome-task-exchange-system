package toughdevschool.ates.analytics.domain.task.cost.business

import arrow.core.Either
import org.springframework.stereotype.Service
import software.darkmatter.platform.business.BusinessCheck
import software.darkmatter.platform.data.PagingRepository
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.security.service.AuthCrudService
import software.darkmatter.platform.syntax.leftIfNull
import toughdevschool.ates.analytics.domain.task.cost.data.TaskCost
import toughdevschool.ates.analytics.domain.task.cost.data.TaskCostRepository
import java.util.UUID

@Service
class Service(
    private val repository: TaskCostRepository,
    pagingRepository: PagingRepository<TaskCost, Long>,
) : AuthCrudService<TaskCost, Long, TaskCostCreate, TaskCostUpdate>(repository, pagingRepository),
    TaskCostService {

    override suspend fun getByTaskUuid(taskUuid: UUID): Either<BusinessError, TaskCost> =
        repository.findByTaskUuid(taskUuid).leftIfNull { notFound }

    override suspend fun createEntity(businessCreate: TaskCostCreate) =
        TaskCost(
            taskUuid = businessCreate.taskUuid,
            assignCost = businessCreate.assignCost,
            reward = businessCreate.reward
        )

    override suspend fun updateEntity(businessUpdate: TaskCostUpdate) =
        with(businessUpdate.taskCost) {
            assignCost = businessUpdate.assignCost
            reward = businessUpdate.reward

            this
        }

    override val checksOnCreate = emptyList<BusinessCheck<TaskCostCreate>>()

    override val checksOnUpdate = emptyList<BusinessCheck<TaskCostUpdate>>()

    override fun entityClass() = TaskCost::class
}
