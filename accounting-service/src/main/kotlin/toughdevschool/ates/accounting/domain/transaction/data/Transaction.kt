package toughdevschool.ates.accounting.domain.transaction.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import software.darkmatter.platform.data.Model
import java.math.BigDecimal
import java.time.OffsetDateTime

@Table("transactions")
data class Transaction(
    @Id
    override var id: Long? = null,
    val type: Type,
    val accountId: Long,
    val billingCycleId: Long,
    val description: String?,
    val debit: BigDecimal,
    val credit: BigDecimal,
    val linkedTransactionId: Long?,
    val createdAt: OffsetDateTime,
) : Model<Long> {

    enum class Type {
        Transition,
        Enrollment,
        Withdrawal,
    }
}
