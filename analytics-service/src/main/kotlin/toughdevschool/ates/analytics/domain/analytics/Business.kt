package toughdevschool.ates.analytics.domain.analytics

import software.darkmatter.platform.api.http.ServiceApi
import java.util.UUID

typealias ManagementAnalyticsApi = ServiceApi<Unit, Unit, Unit, Unit>
typealias TaskAnalyticsApi = ServiceApi<Unit, TaskAnalytics, Unit, Unit>

data class TaskAnalytics(
    val mostExpensiveTask: CompletedTask,
) {

    data class CompletedTask(
        val uuid: UUID,
    )
}
