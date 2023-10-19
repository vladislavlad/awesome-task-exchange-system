package toughdevschool.ates.accounting.domain.transaction.log

import org.springframework.data.domain.Pageable
import software.darkmatter.platform.api.http.HttpApi
import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.api.TransactionsLogDto
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.transaction.data.Transaction

typealias TransactionsLogApi = HttpApi<TransactionsLogDto.Request>
typealias TransactionLogService = Service<TransactionLogRequest, TransactionLogResponse>

data class TransactionLogRequest(
    val account: Account?,
    val pageable: Pageable,
)

data class TransactionLogResponse(
    val transactions: List<Transaction>,
)
