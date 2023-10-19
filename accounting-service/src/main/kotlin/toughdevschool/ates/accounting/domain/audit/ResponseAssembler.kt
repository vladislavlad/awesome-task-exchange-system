package toughdevschool.ates.accounting.domain.audit

import arrow.core.right
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.accounting.api.AccountingAuditDto
import toughdevschool.ates.accounting.domain.transaction.log.TransactionLogResponse

@Component
class ResponseAssembler : ResponseAssembler<TransactionLogResponse, AccountingAuditDto.Response> {

    override suspend fun assemble(business: TransactionLogResponse) =
        AccountingAuditDto.Response(
            transactions = business.transactions
                .map {
                    AccountingAuditDto.Response.TransactionDto(
                        type = it.type,
                        billingCycleId = it.billingCycleId,
                        description = it.description,
                        amount = it.debit - it.credit,
                        createdAt = it.createdAt,
                    )
                }
        ).right()
}
