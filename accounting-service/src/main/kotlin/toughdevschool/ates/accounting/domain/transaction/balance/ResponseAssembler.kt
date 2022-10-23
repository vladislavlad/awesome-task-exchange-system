package toughdevschool.ates.accounting.domain.transaction.balance

import arrow.core.right
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.accounting.api.BalanceDto

@Component
class ResponseAssembler : ResponseAssembler<BalanceResponse, BalanceDto.Response> {

    override suspend fun assemble(business: BalanceResponse) = BalanceDto.Response(business.balance).right()
}
