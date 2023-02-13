package toughdevschool.ates.accounting.domain.transaction.transfer

import arrow.core.right
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.accounting.api.TransferDto

@Component
class ResponseAssembler : ResponseAssembler<TransferResponse, TransferDto.Response> {

    override suspend fun assemble(business: TransferResponse) =
        TransferDto.Response(business.createdAt).right()
}
