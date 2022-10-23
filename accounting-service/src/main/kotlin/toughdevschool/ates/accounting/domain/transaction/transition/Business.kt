package toughdevschool.ates.accounting.domain.transaction.transition

import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.transaction.data.Transaction
import java.math.BigDecimal


//data class WithdrawalRequest(
//
//)
//
//data class EnrollmentRequest(
//
//)

typealias TransitionService = Service<TransitionRequest, TransitionResponse>

data class TransitionRequest(
    val source: Account,
    val destination: Account,
    val amount: BigDecimal,
    val description: String?,
)

data class TransitionResponse(
    val transactions: List<Transaction>,
)