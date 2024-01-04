package toughdevschool.ates.accounting.domain.transaction.log

import arrow.core.right
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.accounting.api.TransactionDto
import toughdevschool.ates.accounting.api.TransactionsLogDto

@Component
class ResponseAssembler : ResponseAssembler<TransactionLogResponse, TransactionsLogDto.Response> {

    override suspend fun assemble(business: TransactionLogResponse) =
        TransactionsLogDto.Response(
            transactions = business.transactions
                .map {
                    TransactionDto(
                        type = it.type,
                        billingCycleId = it.billingCycleId,
                        description = it.description,
                        amount = it.debit - it.credit,
                        createdAt = it.createdAt,
                    )
                }
        ).right()
}
