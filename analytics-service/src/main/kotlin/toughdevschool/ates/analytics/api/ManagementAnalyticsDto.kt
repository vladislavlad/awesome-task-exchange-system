package toughdevschool.ates.analytics.api

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

object ManagementAnalyticsDto {

    @Schema(name = "ManagementAnalyticsResponse")
    data class Response(
        val currentDay: AnalyticsForPeriodDto,
    ) {

        @Schema(name = "AnalyticsForPeriodResponse")
        data class AnalyticsForPeriodDto(
            val pnl: Int,
            val usersWithLosses: List<UserWithLossesDto>,
        )

        @Schema(name = "UserWithLossesResponse")
        data class UserWithLossesDto(
            val userUuid: UUID,
            val pnl: Int,
        )
    }
}