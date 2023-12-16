package toughdevschool.ates.accounting.api

import io.swagger.v3.oas.annotations.media.Schema

object TransactionsLogDto {

    data class Request(
        val page: Int,
        val size: Int,
    )

    @Schema(name = "TransactionsLogResponse")
    data class Response(
        val transactions: List<TransactionDto>,
    )
}
