package toughdevschool.ates.accounting.domain.statistics

import software.darkmatter.platform.api.http.HttpApi
import software.darkmatter.platform.service.Service
import java.math.BigDecimal

interface StatisticsApi : HttpApi<Unit>
interface StatisticsService : Service<Unit, StatisticsResponse>

data class StatisticsResponse(
    val balance: BigDecimal,
    val netChanges: BigDecimal,
)
