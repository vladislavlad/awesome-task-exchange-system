package toughdevschool.ates.analytics.domain.analytics.tasks

import arrow.core.Either
import arrow.core.raise.either
import org.springframework.stereotype.Component
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.analytics.domain.analytics.TaskAnalytics
import toughdevschool.ates.analytics.domain.task.crud.business.TaskService

@Component
class Service(
    private val taskService: TaskService,
) : TaskAnalyticsService {

    override suspend fun perform(request: Unit): Either<BusinessError, TaskAnalytics> = either {
        val mostExpensiveTask = taskService.getMostExpensiveTask()
            .map {
                TaskAnalytics.CompletedTask(
                    uuid = it.uuid,
                    title = it.title,
                    assignCost = it.assignCost,
                    reward = it.reward,
                    userUuid = it.userUuid,
                )
            }.bind()

        TaskAnalytics(
            mostExpensiveTask = mostExpensiveTask,
        )
    }
}
