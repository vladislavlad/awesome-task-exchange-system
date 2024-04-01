package toughdevschool.ates.analytics.domain.analytics

import software.darkmatter.platform.api.http.ServiceApi
import toughdevschool.ates.analytics.api.TaskAnalyticsDto
import java.util.UUID

typealias ManagementAnalyticsApi = ServiceApi<Unit, Unit, Unit, Unit>
typealias TaskAnalyticsApi = ServiceApi<Unit, TaskAnalyticsDto.Response, Unit, TaskAnalytics>

data class TaskAnalytics(
    val mostExpensiveTask: CompletedTask,
) {

    data class CompletedTask(
        val uuid: UUID,
        var title: String,
        val assignCost: Int,
        val reward: Int,
        var userUuid: UUID?,
    )
}
