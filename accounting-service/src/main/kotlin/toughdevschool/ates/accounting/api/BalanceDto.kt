package toughdevschool.ates.accounting.api

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

object BalanceDto {

    @Schema(name = "BalanceRequest")
    data class Response(
        val balance: BigDecimal,
    )
}
