package toughdevschool.ates.accounting.api

import java.math.BigDecimal

object StatisticsDto {

    data class Response(
        val balance: BigDecimal,
        val netChanges: BigDecimal,
    )
}
