package toughdevschool.ates.accounting.api

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

object TransferDto {

    data class Request(
        val body: Body,
    ) {

        data class Body(
            val destination: UUID,
            val amount: BigDecimal,
            val description: String?,
        )
    }

    data class Response(
        val createdAt: OffsetDateTime
    )
}
