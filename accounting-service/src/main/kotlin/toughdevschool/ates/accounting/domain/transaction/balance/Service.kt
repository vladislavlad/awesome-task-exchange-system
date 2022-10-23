package toughdevschool.ates.accounting.domain.transaction.balance

import arrow.core.Either
import arrow.core.right
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import software.darkmatter.platform.error.BusinessError
import toughdevschool.ates.accounting.domain.transaction.data.TransactionRepository
import java.math.BigDecimal

@Service
class Service(
    private val transactionRepository: TransactionRepository,
) : BalanceService {

    override suspend fun perform(request: BalanceRequest): Either<BusinessError, BalanceResponse> {
        val transactions = transactionRepository.findAllByAccountId(request.account.id!!)
            .map { it.debit to it.credit }
            .toList()

        val balance = transactions.reduceOrNull { l, r -> (l.first + r.first) to (l.second + r.second) }
            ?.let { it.first - it.second }
            ?: BigDecimal.ZERO

        return BalanceResponse(
            userUuid = request.user.uuid,
            accountId = request.account.id!!,
            balance = balance
        ).right()
    }
}
