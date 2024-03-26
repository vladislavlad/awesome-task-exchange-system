package toughdevschool.ates.event.cud.application.v1

import software.darkmatter.platform.event.KeyAware
import java.util.UUID

data class ApplicationData(
    val uuid: UUID,
    val name: String,
    var title: String,
) : KeyAware {

    override val key = uuid.toString()
}
