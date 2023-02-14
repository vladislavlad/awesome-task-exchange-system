package toughdevschool.ates.event.business.transaction.v1

import software.darkmatter.platform.event.KeyAware
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class TransactionCompleted(
    val publicId: String,
    val type: String,
    val accountUuid: UUID,
    val description: String?,
    val debit: BigDecimal,
    val credit: BigDecimal,
    val createdAt: OffsetDateTime,
) : KeyAware {

    override val key = publicId
}
