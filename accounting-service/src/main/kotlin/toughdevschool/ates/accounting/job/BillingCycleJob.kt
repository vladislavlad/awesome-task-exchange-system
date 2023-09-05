package toughdevschool.ates.accounting.job

import kotlinx.coroutines.runBlocking
import mu.KLogger
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import toughdevschool.ates.accounting.domain.billingCycle.business.BillingCycleService

@Component
class BillingCycleJob(
    val billingCycleService: BillingCycleService,
) {

    private val logger: KLogger = KotlinLogging.logger { }

    @Scheduled(cron = "0 0 0 * * *")
    fun closeBillingCycle() {
        runBlocking {
            billingCycleService.closeCurrentCycle()
        }
        logger.info { "Billing cycle closed" }
    }
}
