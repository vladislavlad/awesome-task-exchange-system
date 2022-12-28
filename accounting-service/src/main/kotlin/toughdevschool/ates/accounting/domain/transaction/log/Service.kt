package toughdevschool.ates.accounting.domain.transaction.log

import arrow.core.right
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import toughdevschool.ates.accounting.domain.transaction.data.TransactionRepository

@Service
class Service(
    private val transactionRepository: TransactionRepository,
) : TransactionLogService {

    override suspend fun perform(request: TransactionLogRequest) =
        TransactionLogResponse(
            transactionRepository.findAllByAccountId(request.account.id!!)
                .toList()
        ).right()
}
