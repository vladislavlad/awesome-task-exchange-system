package toughdevschool.ates.accounting.domain.transaction.data

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface TransactionRepository : CoroutineSortingRepository<Transaction, Long> {

    fun findAllByAccountId(accountId: Long): Flow<Transaction>
}
