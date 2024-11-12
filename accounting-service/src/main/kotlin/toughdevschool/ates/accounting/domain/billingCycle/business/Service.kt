package toughdevschool.ates.accounting.domain.billingCycle.business

import arrow.core.Either
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import software.darkmatter.platform.business.BusinessCheck
import software.darkmatter.platform.error.BusinessError
import software.darkmatter.platform.security.service.AuthCrudService
import software.darkmatter.platform.syntax.leftIfNull
import toughdevschool.ates.accounting.domain.billingCycle.data.BillingCycle
import toughdevschool.ates.accounting.domain.billingCycle.data.BillingCyclePagingRepository
import toughdevschool.ates.accounting.domain.billingCycle.data.BillingCycleRepository
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

@Service
class Service(
    private val repository: BillingCycleRepository,
    pagingRepository: BillingCyclePagingRepository,
) : AuthCrudService<BillingCycle, Long, BillingCycleCreate, BillingCycleUpdate>(repository, pagingRepository),
    BillingCycleService {

    override suspend fun getActive() = repository.findActive().leftIfNull { notFound }

    @Transactional
    override suspend fun closeCurrentCycle(): Either<BusinessError, Unit> =
        getActive()
            .map {
                val startOfDay = OffsetDateTime.now().truncatedTo(ChronoUnit.DAYS)
                update(
                    BillingCycleUpdate(it, startOfDay, BillingCycle.Status.Closed)
                )
                create(
                    BillingCycleCreate(startOfDay, null)
                )

                Unit
            }

    override suspend fun createEntity(businessCreate: BillingCycleCreate) =
        BillingCycle(
            status = BillingCycle.Status.Active,
            startDatetime = businessCreate.startDatetime,
            endDatetime = businessCreate.endDatetime,
        )

    override suspend fun updateEntity(businessUpdate: BillingCycleUpdate) =
        with(businessUpdate.billingCycle) {
            endDatetime = businessUpdate.endDatetime
            status = businessUpdate.status
            this
        }

    override val checksOnCreate: List<BusinessCheck<BillingCycleCreate>> = emptyList()

    override val checksOnUpdate: List<BusinessCheck<BillingCycleUpdate>> = emptyList()

    override fun entityClass() = BillingCycle::class
}
