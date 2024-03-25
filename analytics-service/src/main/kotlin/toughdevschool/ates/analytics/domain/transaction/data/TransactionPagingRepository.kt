package toughdevschool.ates.analytics.domain.transaction.data

import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.stereotype.Repository
import software.darkmatter.platform.data.PagingRepository

@Repository
class TransactionPagingRepository(
    override val r2dbcOperations: R2dbcEntityOperations
) : PagingRepository<Transaction, Long> {

    override val klass = Transaction::class
}
