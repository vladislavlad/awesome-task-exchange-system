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
            mostExpensiveTask = business.mostExpensiveTask
                .let {
                    CompletedTaskDto(
                        uuid = it.uuid,
                        title = it.title,
                        assignCost = it.assignCost,
                        reward = it.reward,
                        userUuid = it.userUuid,
                    )
                }
        ).right()
}
