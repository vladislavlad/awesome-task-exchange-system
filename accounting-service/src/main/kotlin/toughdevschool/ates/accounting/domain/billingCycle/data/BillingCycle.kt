package toughdevschool.ates.accounting.domain.billingCycle.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import software.darkmatter.platform.data.Model
import java.time.OffsetDateTime

@Table("billing_cycles")
data class BillingCycle(
    @Id
    override var id: Long? = null,
    var status: Status,
    val startDatetime: OffsetDateTime,
    var endDatetime: OffsetDateTime?,
) : Model<Long> {

    enum class Status {
        Active,
        Closed,
    }
}
