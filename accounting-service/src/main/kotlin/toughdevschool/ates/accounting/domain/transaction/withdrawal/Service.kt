package toughdevschool.ates.accounting.domain.transaction.withdrawal

import arrow.core.raise.either
import org.springframework.stereotype.Service
import toughdevschool.ates.accounting.domain.account.CashOperationsAccountUuid
import toughdevschool.ates.accounting.domain.account.business.AccountService
import toughdevschool.ates.accounting.domain.transaction.data.Transaction
import toughdevschool.ates.accounting.domain.transaction.transfer.TransferRequest
import toughdevschool.ates.accounting.domain.transaction.transfer.TransferService

@Service
class Service(
    private val accountService: AccountService,
    private val transferService: TransferService,
) : WithdrawalService {

    override suspend fun perform(request: WithdrawalRequest) = either {
        val cashOperationAccounts = accountService.getByUuid(CashOperationsAccountUuid).bind()

        val transferResult = transferService.perform(
            TransferRequest(Transaction.Type.Withdrawal, request.source, cashOperationAccounts, request.amount, request.description)
        ).bind()

        WithdrawalResponse(
            transferResult.transactions,
            transferResult.createdAt,
        )
    }
}
