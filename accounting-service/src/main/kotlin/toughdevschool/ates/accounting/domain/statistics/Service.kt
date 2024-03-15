package toughdevschool.ates.accounting.domain.statistics

import arrow.core.raise.either
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import toughdevschool.ates.accounting.domain.account.CompanyAccountUuid
import toughdevschool.ates.accounting.domain.account.business.AccountService
import toughdevschool.ates.accounting.domain.billingCycle.business.BillingCycleService
import toughdevschool.ates.accounting.domain.transaction.balance.sumDebitAndCredit
import toughdevschool.ates.accounting.domain.transaction.log.TransactionLogRequest
import toughdevschool.ates.accounting.domain.transaction.log.TransactionLogService

@Service
class Service(
    private val accountService: AccountService,
    private val transactionLogService: TransactionLogService,
    private val billingCycleService: BillingCycleService,
) : StatisticsService {

    override suspend fun perform(request: Unit) = either {
        val currentBillingCycle = billingCycleService.getActive().bind()
        val companyAccount = accountService.getByUuid(CompanyAccountUuid).bind()
        val transactionLog = transactionLogService.perform(TransactionLogRequest(companyAccount, Pageable.unpaged())).bind()
        val balance = transactionLog.transactions
            .sumDebitAndCredit()
        val netChanges = transactionLog.transactions
            .filter { it.billingCycleId == currentBillingCycle.id }
            .sumDebitAndCredit()

        StatisticsResponse(balance = balance, netChanges = netChanges)
    }
}
