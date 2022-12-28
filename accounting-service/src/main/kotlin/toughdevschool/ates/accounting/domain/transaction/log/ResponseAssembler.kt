package toughdevschool.ates.accounting.domain.transaction.log

import arrow.core.right
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.accounting.api.AuditLogDto

@Component
class ResponseAssembler : ResponseAssembler<TransactionLogResponse, AuditLogDto.Response> {

    override suspend fun assemble(business: TransactionLogResponse) =
        AuditLogDto.Response(
            transactions = business.transactions
                .map {
                    AuditLogDto.Response.TransactionDto(
                        type = it.type,
                        billingCycleId = it.billingCycleId,
                        description = it.description,
                        amount = it.debit - it.credit,
                        createdAt = it.createdAt,
                    )
                }
        ).right()
}
