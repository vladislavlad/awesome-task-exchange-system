package toughdevschool.ates.accounting.domain.transaction.enrollment

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
) : EnrollmentService {

    override suspend fun perform(request: EnrollmentRequest) = either {
        val cashOperationAccounts = accountService.getByUuid(CashOperationsAccountUuid).bind()

        val transferResult = transferService.perform(
            TransferRequest(Transaction.Type.Enrollment, cashOperationAccounts, request.destination, request.amount, request.description)
        ).bind()

        EnrollmentResponse(
            transferResult.transactions,
            transferResult.createdAt,
        )
    }
}
