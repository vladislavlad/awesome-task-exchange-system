package toughdevschool.ates.analytics.domain.userRole.data

import org.springframework.data.r2dbc.repository.Modifying
import software.darkmatter.platform.data.CoroutineCrudSortingRepository

interface UserRoleRepository : CoroutineCrudSortingRepository<UserRole, Long> {

    @Modifying
    suspend fun deleteAllByUserIdAndRoleIn(userId: Long, roles: List<String>)
}
