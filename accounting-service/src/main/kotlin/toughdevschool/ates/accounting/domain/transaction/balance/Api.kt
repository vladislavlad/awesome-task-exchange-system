package toughdevschool.ates.accounting.domain.transaction.balance

import org.springframework.stereotype.Component
import software.darkmatter.platform.api.http.ServiceApi
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.api.BalanceDto

@Component
class Api(
    override val requestAssembler: RequestAssembler<Unit, BalanceRequest>,
    override val service: Service<BalanceRequest, BalanceResponse>,
    override val responseAssembler: ResponseAssembler<BalanceResponse, BalanceDto.Response>,
) : ServiceApi<Unit, BalanceDto.Response, BalanceRequest, BalanceResponse>(),
    BalanceApi
