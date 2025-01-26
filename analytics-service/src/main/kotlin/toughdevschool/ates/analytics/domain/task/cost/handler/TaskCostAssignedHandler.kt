package toughdevschool.ates.analytics.domain.task.cost.handler

import arrow.core.Either
import arrow.core.raise.either
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.syntax.UnitRight
import toughdevschool.ates.analytics.domain.task.cost.business.TaskCostCreate
import toughdevschool.ates.analytics.domain.task.cost.business.TaskCostService
import toughdevschool.ates.event.business.task.v1.TaskCostAssigned

@Component
class TaskCostAssignedHandler(
    private val taskCostService: TaskCostService,
) {

    @Transactional
    suspend fun handle(data: TaskCostAssigned): Either<BusinessError, Unit> = either {
        taskCostService.create(
            TaskCostCreate(
                taskUuid = data.taskUuid,
                assignCost = data.assignCost,
                reward = data.reward,
            )
        ).bind()

        UnitRight
    }
}
