package toughdevschool.ates.accounting.api

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

object AccountingAuditDto {

    data class Request(
        val body: Body,
        val page: Int,
        val size: Int,
    ) {

        @Schema(name = "AccountingAuditRequest")
        data class Body(
            val accountUuid: UUID?,
        )
    }

    @Schema(name = "AccountingAuditResponse")
    data class Response(
        val transactions: List<TransactionDto>,
    )
}
