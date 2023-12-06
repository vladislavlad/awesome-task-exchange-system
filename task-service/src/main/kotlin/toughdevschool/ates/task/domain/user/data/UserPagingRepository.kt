package toughdevschool.ates.task.domain.user.data

import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.stereotype.Repository
import software.darkmatter.platform.data.PagingRepository

@Repository
class UserPagingRepository(
    override val r2dbcOperations: R2dbcEntityOperations
) : PagingRepository<User, Long> {

    override val klass = User::class
}
