package toughdevschool.ates.accounting.domain.statistics

import org.springframework.stereotype.Component
import software.darkmatter.platform.api.http.ServiceApi
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import software.darkmatter.platform.service.Service
import toughdevschool.ates.accounting.api.StatisticsDto

@Component
class Api(
    override val requestAssembler: RequestAssembler<Unit, Unit>,
    override val service: Service<Unit, StatisticsResponse>,
    override val responseAssembler: ResponseAssembler<StatisticsResponse, StatisticsDto.Response>,
) : ServiceApi<Unit, StatisticsDto.Response, Unit, StatisticsResponse>(),
    StatisticsApi
