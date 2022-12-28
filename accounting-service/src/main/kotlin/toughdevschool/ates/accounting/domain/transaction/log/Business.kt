package toughdevschool.ates.accounting.domain.transaction.log

import software.darkmatter.platform.api.http.HttpApi
import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.api.AuditLogDto
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.transaction.data.Transaction

typealias AuditLogApi = HttpApi<AuditLogDto.Request>
typealias TransactionLogService = Service<TransactionLogRequest, TransactionLogResponse>

data class TransactionLogRequest(
    val account: Account,
)

data class TransactionLogResponse(
    val transactions: List<Transaction>,
)
