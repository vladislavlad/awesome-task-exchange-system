package toughdevschool.ates.accounting.domain.billingCycle.data

import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.stereotype.Repository
import software.darkmatter.platform.data.PagingRepository

@Repository
class BillingCyclePagingRepository(
    override val r2dbcOperations: R2dbcEntityOperations
) : PagingRepository<BillingCycle, Long> {

    override val klass = BillingCycle::class
}
