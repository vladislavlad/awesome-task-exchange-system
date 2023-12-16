package toughdevschool.ates.accounting.api

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

object StatisticsDto {

    @Schema(name = "StatisticsResponse")
    data class Response(
        val balance: BigDecimal,
        val netChanges: BigDecimal,
    )
}
