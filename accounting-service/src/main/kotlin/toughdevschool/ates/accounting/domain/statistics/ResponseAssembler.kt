package toughdevschool.ates.accounting.domain.statistics

import arrow.core.right
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.accounting.api.StatisticsDto

@Component
class ResponseAssembler : ResponseAssembler<StatisticsResponse, StatisticsDto.Response> {

    override suspend fun assemble(business: StatisticsResponse) = StatisticsDto.Response(
        balance = business.balance,
        netChanges = business.netChanges,
    ).right()
}
