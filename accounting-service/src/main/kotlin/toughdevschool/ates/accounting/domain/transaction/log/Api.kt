package toughdevschool.ates.accounting.domain.transaction.log

import org.springframework.stereotype.Component
import software.darkmatter.platform.api.http.ServiceApi
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.api.TransactionsLogDto

@Component
class Api(
    override val requestAssembler: RequestAssembler<TransactionsLogDto.Request, TransactionLogRequest>,
    override val service: Service<TransactionLogRequest, TransactionLogResponse>,
    override val responseAssembler: ResponseAssembler<TransactionLogResponse, TransactionsLogDto.Response>,
) : ServiceApi<TransactionsLogDto.Request, TransactionsLogDto.Response, TransactionLogRequest, TransactionLogResponse>(),
    TransactionsLogApi
