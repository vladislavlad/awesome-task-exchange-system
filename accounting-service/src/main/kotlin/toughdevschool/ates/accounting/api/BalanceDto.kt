package toughdevschool.ates.accounting.api

import java.math.BigDecimal

object BalanceDto {

    data class Response(
        val balance: BigDecimal,
    )
}
