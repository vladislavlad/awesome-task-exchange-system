package toughdevschool.ates.event.business

import toughdevschool.ates.event.Event
import toughdevschool.ates.event.KeyAware
import toughdevschool.ates.event.business.task.v1.TaskAssigned
import toughdevschool.ates.event.business.task.v1.TaskCompleted
import toughdevschool.ates.event.business.user.v1.UserRolesChanged
import kotlin.reflect.KClass

object BusinessEventRegistry {

    val businessEvents: Map<Event.Type<Type>, KClass<out KeyAware>> = mapOf(
        Type.TaskAssigned.version(1) to TaskAssigned::class,
        Type.TaskCompleted.version(1) to TaskCompleted::class,
        Type.UserRolesChanged.version(1) to UserRolesChanged::class,
    )

    private fun Type.version(version: Int) = Event.Type(this, version)
}
