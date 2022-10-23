package toughdevschool.ates.accounting.domain.transaction.balance

import software.darkmatter.platform.api.http.HttpApi
import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.user.data.User
import java.math.BigDecimal
import java.util.UUID

typealias BalanceApi = HttpApi<Unit>
typealias BalanceService = Service<BalanceRequest, BalanceResponse>

data class BalanceRequest(
    val user: User,
    val account: Account,
)

data class BalanceResponse(
    val userUuid: UUID,
    val accountId: Long,
    val balance: BigDecimal,
)
