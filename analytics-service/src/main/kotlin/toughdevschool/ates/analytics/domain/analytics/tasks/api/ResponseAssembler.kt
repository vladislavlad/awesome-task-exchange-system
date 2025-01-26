package toughdevschool.ates.analytics.domain.analytics.tasks.api

import arrow.core.Either
import arrow.core.right
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.analytics.api.TaskAnalyticsDto
import toughdevschool.ates.analytics.api.TaskAnalyticsDto.Response.CompletedTaskDto
import toughdevschool.ates.analytics.domain.analytics.TaskAnalytics

@Component
class ResponseAssembler : ResponseAssembler<TaskAnalytics, TaskAnalyticsDto.Response> {

    override suspend fun assemble(business: TaskAnalytics): Either<BusinessError, TaskAnalyticsDto.Response> =
        TaskAnalyticsDto.Response(
            mostExpensiveTasks = with(business.mostExpensiveTasks) {
                TaskAnalyticsDto.Response.MostExpensiveTasksDto(
                    forDay = forDay?.toDto(),
                    forWeek = forWeek?.toDto(),
                    forMonth = forMonth?.toDto(),
                )
            }
        ).right()

    private fun TaskAnalytics.CompletedTask.toDto() =
        CompletedTaskDto(
            uuid = uuid,
            title = title,
            assignCost = assignCost,
            reward = reward,
            userUuid = userUuid,
        )
}
