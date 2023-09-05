package toughdevschool.ates.accounting.domain.billingCycle.business

import arrow.core.Either
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.service.CrudService
import toughdevschool.ates.accounting.domain.billingCycle.data.BillingCycle

interface BillingCycleService : CrudService<BillingCycle, Long, BillingCycleCreate, BillingCycleUpdate> {

    suspend fun getActive(): Either<BusinessError, BillingCycle>

    suspend fun closeCurrentCycle(): Either<BusinessError, Unit>
}