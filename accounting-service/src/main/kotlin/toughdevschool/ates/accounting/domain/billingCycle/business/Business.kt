package toughdevschool.ates.accounting.domain.billingCycle.business

import toughdevschool.ates.accounting.domain.billingCycle.data.BillingCycle
import java.time.OffsetDateTime

data class BillingCycleCreate(
    val startDatetime: OffsetDateTime,
    val endDatetime: OffsetDateTime?,
)

data class BillingCycleUpdate(
    val billingCycle: BillingCycle,
    val endDatetime: OffsetDateTime,
    val status: BillingCycle.Status,
)
