package toughdevschool.ates.accounting.job

import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.runBlocking
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
