package toughdevschool.ates.accounting.domain.transaction.log

import org.springframework.stereotype.Component
import software.darkmatter.platform.api.http.ServiceApi
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.api.AuditLogDto

@Component
class Api(
    override val requestAssembler: RequestAssembler<AuditLogDto.Request, TransactionLogRequest>,
    override val service: Service<TransactionLogRequest, TransactionLogResponse>,
    override val responseAssembler: ResponseAssembler<TransactionLogResponse, AuditLogDto.Response>,
) : ServiceApi<AuditLogDto.Request, AuditLogDto.Response, TransactionLogRequest, TransactionLogResponse>(),
    AuditLogApi
