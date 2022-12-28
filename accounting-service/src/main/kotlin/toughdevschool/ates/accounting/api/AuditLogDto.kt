package toughdevschool.ates.accounting.api

import toughdevschool.ates.accounting.domain.transaction.data.Transaction
import java.math.BigDecimal
import java.time.OffsetDateTime

object AuditLogDto {

    object Request

    data class Response(
        val transactions: List<TransactionDto>,
    ) {

        data class TransactionDto(
            val type: Transaction.Type,
            val billingCycleId: Long,
            val description: String?,
            val amount: BigDecimal,
            val createdAt: OffsetDateTime,
        )
    }
}
