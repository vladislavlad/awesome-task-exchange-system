package toughdevschool.ates.accounting.domain.transaction.log

import arrow.core.continuations.either
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.security.context.jwtAuthenticationFromSecurityContext
import toughdevschool.ates.accounting.api.AuditLogDto
import toughdevschool.ates.accounting.domain.account.business.AccountService
import toughdevschool.ates.accounting.domain.user.business.UserService

@Component
class RequestAssembler(
    private val userService: UserService,
    private val accountService: AccountService,
) : RequestAssembler<AuditLogDto.Request, TransactionLogRequest> {

    override suspend fun assemble(request: AuditLogDto.Request) = either {
        val auth = jwtAuthenticationFromSecurityContext().bind()
        val user = userService.getByUuid(auth.jwt.subject).bind()
        val account = accountService.getByUser(user).bind()

        TransactionLogRequest(account)
    }
}
