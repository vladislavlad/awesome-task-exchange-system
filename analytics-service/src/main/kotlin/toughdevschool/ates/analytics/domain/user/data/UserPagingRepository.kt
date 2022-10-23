package toughdevschool.ates.analytics.domain.user.data

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository
import software.darkmatter.platform.data.PagingRepository

@Repository
class UserPagingRepository(
    override val r2dbcTemplate: R2dbcEntityTemplate
) : PagingRepository<User, Long> {

    override val klass = User::class
}
