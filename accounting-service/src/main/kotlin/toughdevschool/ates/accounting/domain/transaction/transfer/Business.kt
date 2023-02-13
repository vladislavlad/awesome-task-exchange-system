package toughdevschool.ates.accounting.domain.transaction.transfer

import software.darkmatter.platform.api.http.HttpApi
import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.api.TransferDto
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.transaction.data.Transaction
import java.math.BigDecimal
import java.time.OffsetDateTime

typealias TransferApi = HttpApi<TransferDto.Request>
typealias TransferService = Service<TransferRequest, TransferResponse>

data class TransferRequest(
    val type: Transaction.Type,
    val source: Account,
    val destination: Account,
    val amount: BigDecimal,
    val description: String?,
)

data class TransferResponse(
    val transactions: List<Transaction>,
    val createdAt: OffsetDateTime,
)