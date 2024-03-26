package toughdevschool.ates.analytics.domain.transaction.data

import software.darkmatter.platform.data.CoroutineCrudSortingRepository

interface TransactionRepository : CoroutineCrudSortingRepository<Transaction, Long>
