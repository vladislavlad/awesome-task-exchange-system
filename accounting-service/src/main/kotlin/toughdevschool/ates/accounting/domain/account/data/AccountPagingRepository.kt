package toughdevschool.ates.accounting.domain.account.data

import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.stereotype.Repository
import software.darkmatter.platform.data.PagingRepository

@Repository
class AccountPagingRepository(
    override val r2dbcOperations: R2dbcEntityOperations
) : PagingRepository<Account, Long> {

    override val klass = Account::class
}
