package toughdevschool.ates.task.domain.user.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import software.darkmatter.platform.data.Model
import java.util.UUID

@Table("users")
data class User(
    @Id
    override var id: Long? = null,
    val uuid: UUID,
    val username: String,
    var firstName: String?,
    var lastName: String?,
    var middleName: String?,
) : Model<Long>
