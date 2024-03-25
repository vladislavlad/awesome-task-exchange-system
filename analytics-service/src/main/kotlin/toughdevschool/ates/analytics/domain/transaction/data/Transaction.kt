package toughdevschool.ates.analytics.domain.transaction.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import software.darkmatter.platform.data.Model
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

@Table("transactions")
data class Transaction(
    @Id
    override var id: Long? = null,
    val publicId: String,
    val type: Type,
    val accountUuid: UUID,
    val debit: BigDecimal,
    val credit: BigDecimal,
    val completedAt: OffsetDateTime,
    val createdAt: OffsetDateTime,
) : Model<Long> {

    enum class Type {
        Transfer,
        Enrollment,
        Withdrawal,
    }
}
