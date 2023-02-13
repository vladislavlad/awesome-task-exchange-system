package toughdevschool.ates.accounting.domain.transaction.transfer

import arrow.core.continuations.either
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.security.context.jwtAuthenticationFromSecurityContext
import toughdevschool.ates.accounting.api.TransferDto
import toughdevschool.ates.accounting.domain.account.business.AccountService
import toughdevschool.ates.accounting.domain.transaction.data.Transaction
import toughdevschool.ates.accounting.domain.user.business.UserService

@Component
class RequestAssembler(
    private val userService: UserService,
    private val accountService: AccountService,
) : RequestAssembler<TransferDto.Request, TransferRequest> {

    override suspend fun assemble(request: TransferDto.Request) = either {
        val auth = jwtAuthenticationFromSecurityContext().bind()
        val user = userService.getByUuid(auth.jwt.subject).bind()
        val account = accountService.getByUser(user).bind()

        val destinationAccount = accountService.getByUuid(request.body.destination).bind()

        TransferRequest(Transaction.Type.Transfer, account, destinationAccount, request.body.amount, request.body.description)
    }
}
