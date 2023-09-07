package toughdevschool.ates.accounting.domain.transaction.balance

import arrow.core.Either
import arrow.core.right
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.accounting.domain.transaction.data.TransactionRepository

@Service
class Service(
    private val transactionRepository: TransactionRepository,
) : BalanceService {

    override suspend fun perform(request: BalanceRequest): Either<BusinessError, BalanceResponse> {
        val transactions = transactionRepository.findAllByAccountId(request.account.id!!)
            .toList()

        val balance = transactions.sumDebitAndCredit()

        return BalanceResponse(
            userUuid = request.user.uuid,
            accountId = request.account.id!!,
            balance = balance
        ).right()
    }
}
