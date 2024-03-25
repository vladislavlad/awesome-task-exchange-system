package toughdevschool.ates.analytics.domain.transaction.data

import kotlinx.coroutines.flow.Flow
import software.darkmatter.platform.data.CoroutineCrudSortingRepository

interface TransactionRepository : CoroutineCrudSortingRepository<Transaction, Long> {

    fun findAllByAccountId(accountId: Long): Flow<Transaction>
}
