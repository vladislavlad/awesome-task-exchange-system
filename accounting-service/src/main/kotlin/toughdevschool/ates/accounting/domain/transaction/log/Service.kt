package toughdevschool.ates.accounting.domain.transaction.log

import arrow.core.right
import kotlinx.coroutines.flow.toList
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Service
import software.darkmatter.platform.data.PagingRepository
import toughdevschool.ates.accounting.domain.account.data.Account
import toughdevschool.ates.accounting.domain.transaction.data.Transaction

@Service
class Service(
    private val transactionPagingRepository: PagingRepository<Transaction, Long>,
) : TransactionLogService {

    override suspend fun perform(request: TransactionLogRequest) =
        TransactionLogResponse(
            transactionPagingRepository.findAllByQuery(
                queryByAccountId(request.account),
                request.pageable,
            ).toList()
        ).right()

    private fun queryByAccountId(account: Account?): Query {
        return account?.let { Query.query(where("accountId").`is`(it.id!!)) }
            ?: Query.empty()
    }
}
