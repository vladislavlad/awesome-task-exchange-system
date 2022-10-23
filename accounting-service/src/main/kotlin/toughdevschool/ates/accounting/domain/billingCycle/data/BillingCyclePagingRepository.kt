package toughdevschool.ates.accounting.domain.billingCycle.data

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository
import software.darkmatter.platform.data.PagingRepository

@Repository
class BillingCyclePagingRepository(
    override val r2dbcTemplate: R2dbcEntityTemplate
) : PagingRepository<BillingCycle, Long> {

    override val klass = BillingCycle::class
}
