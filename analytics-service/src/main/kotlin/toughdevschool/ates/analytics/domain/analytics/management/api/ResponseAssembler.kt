package toughdevschool.ates.analytics.domain.analytics.management.api

import arrow.core.Either
import arrow.core.right
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.analytics.api.ManagementAnalyticsDto
import java.util.UUID

@Component
class ResponseAssembler : ResponseAssembler<Unit, ManagementAnalyticsDto.Response> {

    override suspend fun assemble(business: Unit): Either<BusinessError, ManagementAnalyticsDto.Response> =
        ManagementAnalyticsDto.Response(
            currentDay = ManagementAnalyticsDto.Response.AnalyticsForPeriodDto(
                pnl = 1,
                usersWithLosses = listOf(
                    ManagementAnalyticsDto.Response.UserWithLossesDto(
                        userUuid = UUID.randomUUID(),
                        pnl = -1
                    )
                ),
            )
        ).right()
}
