package toughdevschool.ates.analytics.api

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

object TaskAnalyticsDto {

    @Schema(name = "TaskAnalyticsResponse")
    data class Response(
        val mostExpensiveTask: CompletedTaskDto,
    ) {

        data class CompletedTaskDto(
            val uuid: UUID,
            var title: String,
            val assignCost: Int,
            val reward: Int,
            var userUuid: UUID?,
        )
    }
}
