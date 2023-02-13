package toughdevschool.ates.accounting.domain.transaction.enrollment

import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.transaction.data.Transaction
import java.math.BigDecimal
import java.time.OffsetDateTime

typealias EnrollmentService = Service<EnrollmentRequest, EnrollmentResponse>

data class EnrollmentRequest(
    val destination: Account,
    val amount: BigDecimal,
    val description: String?,
)

data class EnrollmentResponse(
    val transactions: List<Transaction>,
    val createdAt: OffsetDateTime,
)
