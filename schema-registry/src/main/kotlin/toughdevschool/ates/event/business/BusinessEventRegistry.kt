package toughdevschool.ates.event.business

import software.darkmatter.event.Event
import software.darkmatter.event.KeyAware
import toughdevschool.ates.event.business.task.v1.TaskAssigned
import toughdevschool.ates.event.business.task.v1.TaskCompleted
import toughdevschool.ates.event.business.user.v1.UserRolesChanged
import kotlin.reflect.KClass

object BusinessEventRegistry {

    val businessEvents: Map<Event.Type<BusinessEventType>, KClass<out KeyAware>> = mapOf(
        BusinessEventType.TaskAssigned.version(1) to TaskAssigned::class,
        BusinessEventType.TaskCompleted.version(1) to TaskCompleted::class,
        BusinessEventType.UserRolesChanged.version(1) to UserRolesChanged::class,
    )

    private fun BusinessEventType.version(version: Int) = Event.Type(this, version)
}
