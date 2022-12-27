package toughdevschool.ates.task.domain.user.data

import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.Query
import software.darkmatter.platform.data.CoroutineCrudSortingRepository
import java.util.UUID

interface UserRepository : CoroutineCrudSortingRepository<User, Long> {

    suspend fun findByUsername(username: String): User?

    suspend fun findByUuid(uuid: UUID): User?

    @Query(
        """
            select count(u)
            from users u 
                left join user_roles ur on ur.user_id = u.id where ur.role in (:roles)
        """
    )
    suspend fun countWithRoleIn(roles: List<String>): Long

    @Query(
        """
            select u.*
            from users u 
                left join user_roles ur on ur.user_id = u.id where ur.role in (:roles)
            limit :limit offset :offset
        """
    )
    fun findAllWithRoleIn(roles: List<String>, limit: Int, offset: Long): Flow<User>
}
