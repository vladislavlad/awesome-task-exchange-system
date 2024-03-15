package toughdevschool.ates.accounting.domain.audit

import arrow.core.raise.either
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import toughdevschool.ates.accounting.api.AccountingAuditDto
import toughdevschool.ates.accounting.domain.account.business.AccountService
import toughdevschool.ates.accounting.domain.transaction.log.TransactionLogRequest

@Component
class RequestAssembler(
    private val accountService: AccountService,
) : RequestAssembler<AccountingAuditDto.Request, TransactionLogRequest> {

    override suspend fun assemble(request: AccountingAuditDto.Request) = either {
        val account = request.body.accountUuid?.let {
            accountService.getByUuid(it).bind()
        }

        TransactionLogRequest(account, Pageable.ofSize(request.size).withPage(request.page))
    }
}
