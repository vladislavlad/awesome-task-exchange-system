package toughdevschool.ates.accounting.domain.transaction.withdrawal

import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.transaction.data.Transaction
import java.math.BigDecimal
import java.time.OffsetDateTime

typealias WithdrawalService = Service<WithdrawalRequest, WithdrawalResponse>

data class WithdrawalRequest(
    val source: Account,
    val amount: BigDecimal,
    val description: String?,
)

data class WithdrawalResponse(
    val transactions: List<Transaction>,
    val createdAt: OffsetDateTime,
)
