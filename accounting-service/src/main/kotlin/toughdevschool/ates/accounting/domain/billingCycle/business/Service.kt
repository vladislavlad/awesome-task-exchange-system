package toughdevschool.ates.accounting.domain.billingCycle.business

import org.springframework.stereotype.Service
import software.darkmatter.platform.business.BusinessChecks
import software.darkmatter.platform.service.AbstractCrudService
import software.darkmatter.platform.syntax.leftIfNull
import toughdevschool.ates.accounting.domain.billingCycle.data.BillingCycle
import toughdevschool.ates.accounting.domain.billingCycle.data.BillingCyclePagingRepository
import toughdevschool.ates.accounting.domain.billingCycle.data.BillingCycleRepository

@Service
class Service(
    private val repository: BillingCycleRepository,
    pagingRepository: BillingCyclePagingRepository,
) : AbstractCrudService<BillingCycle, Long, BillingCycleCreate, BillingCycleUpdate>(repository, pagingRepository),
    BillingCycleService {

    override suspend fun getActive() = repository.findActive().leftIfNull { notFound }

    override suspend fun createEntity(businessCreate: BillingCycleCreate) =
        BillingCycle(
            status = BillingCycle.Status.Active,
            startDatetime = businessCreate.startDatetime,
            endDatetime = businessCreate.endDatetime,
        )

    override suspend fun updateEntity(businessUpdate: BillingCycleUpdate) =
        with(businessUpdate.billingCycle) {
            endDatetime = businessUpdate.endDatetime
            this
        }

    override val checksOnCreate: BusinessChecks<BillingCycleCreate> = emptyList()

    override val checksOnUpdate: BusinessChecks<BillingCycleUpdate> = emptyList()

    override fun entityClass() = BillingCycle::class
}
