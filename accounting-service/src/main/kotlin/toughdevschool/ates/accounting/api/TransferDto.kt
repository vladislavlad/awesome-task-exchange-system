package toughdevschool.ates.accounting.api

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

object TransferDto {

    data class Request(
        val body: Body,
    ) {

        @Schema(name = "TransferRequest")
        data class Body(
            val destination: UUID,
            val amount: BigDecimal,
            val description: String?,
        )
    }

    @Schema(name = "TransferResponse")
    data class Response(
        val createdAt: OffsetDateTime
    )
}
