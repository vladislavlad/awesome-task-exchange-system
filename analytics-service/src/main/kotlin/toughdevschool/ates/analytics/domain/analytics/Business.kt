package toughdevschool.ates.analytics.domain.analytics

import software.darkmatter.platform.api.http.ServiceApi
import toughdevschool.ates.analytics.api.ManagementAnalyticsDto
import toughdevschool.ates.analytics.api.TaskAnalyticsDto
import java.util.UUID

typealias ManagementAnalyticsApi = ServiceApi<Unit, ManagementAnalyticsDto.Response, Unit, Unit>
typealias TaskAnalyticsApi = ServiceApi<Unit, TaskAnalyticsDto.Response, Unit, TaskAnalytics>

data class TaskAnalytics(
    val mostExpensiveTasks: MostExpensiveTasks,
) {

    data class MostExpensiveTasks(
        val forDay: CompletedTask?,
        val forWeek: CompletedTask?,
        val forMonth: CompletedTask?,
    )

    data class CompletedTask(
        val uuid: UUID,
        var title: String,
        val assignCost: Int,
        val reward: Int,
        var userUuid: UUID?,
    )
}
