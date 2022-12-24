package toughdevschool.ates.accounting.domain.account.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import software.darkmatter.platform.data.Model
import java.util.UUID

@Table("accounts")
data class Account(
    @Id
    override var id: Long? = null,
    val uuid: UUID,
    val type: Type,
    var description: String?,
    var status: Status,
    val userId: Long?,
) : Model<Long> {

    enum class Type {
        User,
        Business,
    }

    enum class Status {
        Active,
        Suspended,
        Blocked,
        Deleted,
    }
}
