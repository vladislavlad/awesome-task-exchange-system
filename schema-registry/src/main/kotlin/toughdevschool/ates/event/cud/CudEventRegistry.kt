package toughdevschool.ates.event.cud

import toughdevschool.ates.event.Event
import toughdevschool.ates.event.KeyAware
import toughdevschool.ates.event.cud.user.v1.UserData
import toughdevschool.ates.event.cud.userRole.v1.UserRoleData
import kotlin.reflect.KClass
import toughdevschool.ates.event.cud.task.v1.TaskData as TaskDataV1
import toughdevschool.ates.event.cud.task.v2.TaskData as TaskDataV2

object CudEventRegistry {

    val cudEvents: Map<Event.Type<Type>, KClass<out KeyAware>> = mapOf(
        Type.User.version(1) to UserData::class,
        Type.UserRole.version(1) to UserRoleData::class,
        Type.Task.version(1) to TaskDataV1::class,
        Type.Task.version(2) to TaskDataV2::class,
    )

    private fun Type.version(version: Int) = Event.Type(this, version)
}
