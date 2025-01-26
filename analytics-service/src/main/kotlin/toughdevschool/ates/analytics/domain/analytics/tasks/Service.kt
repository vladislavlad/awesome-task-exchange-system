package toughdevschool.ates.analytics.domain.analytics.tasks

import arrow.core.Either
import arrow.core.raise.either
import org.springframework.stereotype.Component
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.analytics.domain.analytics.TaskAnalytics
import toughdevschool.ates.analytics.domain.task.crud.business.Scale
import toughdevschool.ates.analytics.domain.task.crud.business.TaskService
import toughdevschool.ates.analytics.domain.task.crud.data.withCost.TaskWithCosts

@Component
class Service(
    private val taskService: TaskService,
) : TaskAnalyticsService {

    override suspend fun perform(request: Unit): Either<BusinessError, TaskAnalytics> = either {
        val mostExpensiveTaskForDay = taskService.getMostExpensiveTask(Scale.Day).bind()
        val mostExpensiveTaskForWeek = taskService.getMostExpensiveTask(Scale.Week).bind()
        val mostExpensiveTaskForMonth = taskService.getMostExpensiveTask(Scale.Month).bind()

        TaskAnalytics(
            mostExpensiveTasks = TaskAnalytics.MostExpensiveTasks(
                forDay = mostExpensiveTaskForDay?.toBusinessModel(),
                forWeek = mostExpensiveTaskForWeek?.toBusinessModel(),
                forMonth = mostExpensiveTaskForMonth?.toBusinessModel(),
            ),
        )
    }

    private fun TaskWithCosts.toBusinessModel(): TaskAnalytics.CompletedTask =
        TaskAnalytics.CompletedTask(
            uuid = taskUuid,
            userUuid = userUuid,
            title = title,
            assignCost = assignCost,
            reward = reward,
        )
}
