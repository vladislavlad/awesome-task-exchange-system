package toughdevschool.ates.accounting.domain.transaction.balance

import software.darkmatter.platform.api.http.HttpApi
import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.transaction.data.Transaction
import toughdevschool.ates.accounting.domain.user.data.User
import java.math.BigDecimal
import java.util.UUID

interface BalanceApi : HttpApi<Unit>
interface BalanceService : Service<BalanceRequest, BalanceResponse>

data class BalanceRequest(
    val user: User,
    val account: Account,
)

data class BalanceResponse(
    val userUuid: UUID,
    val accountId: Long,
    val balance: BigDecimal,
)

fun List<Transaction>.sumDebitAndCredit(): BigDecimal {
    return map { it.debit to it.credit }
        .reduceOrNull { l, r -> (l.first + r.first) to (l.second + r.second) }
        ?.let { it.first - it.second }
        ?: BigDecimal.ZERO
}
