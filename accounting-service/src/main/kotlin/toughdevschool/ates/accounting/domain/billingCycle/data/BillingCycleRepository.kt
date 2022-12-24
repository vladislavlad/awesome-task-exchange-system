package toughdevschool.ates.accounting.domain.billingCycle.data

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface BillingCycleRepository : CoroutineSortingRepository<BillingCycle, Long> {

    @Query(
        """
            select bc.*
            from billing_cycles bc
            where bc.status = 'Active'
                and (bc.end_datetime > now() or bc.end_datetime is null)
            limit 1
        """
    )
    suspend fun findActive(): BillingCycle?
}
