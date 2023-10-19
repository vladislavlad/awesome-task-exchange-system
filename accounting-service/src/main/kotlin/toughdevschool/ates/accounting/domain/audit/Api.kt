package toughdevschool.ates.accounting.domain.audit

import org.springframework.stereotype.Component
import software.darkmatter.platform.api.http.ServiceApi
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.api.AccountingAuditDto
import toughdevschool.ates.accounting.domain.transaction.log.TransactionLogRequest
import toughdevschool.ates.accounting.domain.transaction.log.TransactionLogResponse

@Component
class Api(
    override val requestAssembler: RequestAssembler<AccountingAuditDto.Request, TransactionLogRequest>,
    override val service: Service<TransactionLogRequest, TransactionLogResponse>,
    override val responseAssembler: ResponseAssembler<TransactionLogResponse, AccountingAuditDto.Response>,
) : ServiceApi<AccountingAuditDto.Request, AccountingAuditDto.Response, TransactionLogRequest, TransactionLogResponse>(),
    AccountingAuditLogApi
