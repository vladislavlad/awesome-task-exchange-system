package toughdevschool.ates.accounting.domain.transaction.data

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository
import software.darkmatter.platform.data.PagingRepository

@Repository
class TransactionPagingRepository(
    override val r2dbcTemplate: R2dbcEntityTemplate
) : PagingRepository<Transaction, Long> {

    override val klass = Transaction::class
}
