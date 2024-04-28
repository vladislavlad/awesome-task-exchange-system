package toughdevschool.ates.task.domain.user.data

import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.Query
import software.darkmatter.platform.data.CoroutineUndeletableCrudSortingRepository
import java.util.UUID

interface UserRepository : CoroutineUndeletableCrudSortingRepository<User, Long> {

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

    fun findAllWithRoleIn(roles: List<String>, pageable: Pageable): Flow<User> =
        if (pageable.isUnpaged) findAllWithRoleIn(roles)
        else findAllWithRoleIn(roles, pageable.pageSize, pageable.offset)

    @Query(
        """
            select u.*
            from users u 
                left join user_roles ur on ur.user_id = u.id where ur.role in (:roles)
            limit :limit offset :offset
        """
    )
    fun findAllWithRoleIn(roles: List<String>, limit: Int, offset: Long): Flow<User>

    @Query(
        """
            select u.*
            from users u 
                left join user_roles ur on ur.user_id = u.id where ur.role in (:roles)
        """
    )
    fun findAllWithRoleIn(roles: List<String>): Flow<User>
}
