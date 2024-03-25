package toughdevschool.ates.analytics.domain.transaction.business

import toughdevschool.ates.analytics.domain.transaction.data.Transaction
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class TransactionCreate(
    val publicId: String,
    val type: Transaction.Type,
    val accountUuid: UUID,
    val debit: BigDecimal,
    val credit: BigDecimal,
    val completedAt: OffsetDateTime,
)
