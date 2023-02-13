package toughdevschool.ates.accounting.domain.transaction.transfer

import org.springframework.stereotype.Component
import software.darkmatter.platform.api.http.ServiceApi
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.api.TransferDto

@Component
class Api(
    override val requestAssembler: RequestAssembler<TransferDto.Request, TransferRequest>,
    override val service: Service<TransferRequest, TransferResponse>,
    override val responseAssembler: ResponseAssembler<TransferResponse, TransferDto.Response>,
) : ServiceApi<TransferDto.Request, TransferDto.Response, TransferRequest, TransferResponse>(),
    TransferApi
