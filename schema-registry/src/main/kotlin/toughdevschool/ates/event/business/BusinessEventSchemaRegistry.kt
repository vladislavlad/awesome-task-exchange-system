package toughdevschool.ates.event.business

import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.KeyAware
import software.darkmatter.platform.event.SchemaRegistry
import toughdevschool.ates.event.business.task.v1.TaskAssigned
import toughdevschool.ates.event.business.task.v1.TaskCompleted
import toughdevschool.ates.event.business.transaction.v1.TransactionCompleted
import toughdevschool.ates.event.business.user.v1.UserRolesChanged
import kotlin.reflect.KClass

object BusinessEventSchemaRegistry : SchemaRegistry<BusinessEventType> {

    override val dataTypesMap: Map<Event.Type<BusinessEventType>, KClass<out KeyAware>> = mapOf(
        BusinessEventType.TaskAssigned.version(1) to TaskAssigned::class,
        BusinessEventType.TaskCompleted.version(1) to TaskCompleted::class,
        BusinessEventType.UserRolesChanged.version(1) to UserRolesChanged::class,
        BusinessEventType.TransactionCompleted.version(1) to TransactionCompleted::class,
    )

    override val eventTypeClass: KClass<BusinessEventType> = BusinessEventType::class

    private fun BusinessEventType.version(version: Int) = Event.Type(this, version)
}
