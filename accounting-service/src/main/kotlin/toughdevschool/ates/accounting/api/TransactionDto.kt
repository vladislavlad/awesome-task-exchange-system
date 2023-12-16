package toughdevschool.ates.accounting.api

import io.swagger.v3.oas.annotations.media.Schema
import toughdevschool.ates.accounting.domain.transaction.data.Transaction
import java.math.BigDecimal
import java.time.OffsetDateTime

@Schema(name = "Transaction")
data class TransactionDto(
    val type: Transaction.Type,
    val billingCycleId: Long,
    val description: String?,
    val amount: BigDecimal,
    val createdAt: OffsetDateTime,
)
