package toughdevschool.ates.event.cud

import software.darkmatter.platform.event.Event
import software.darkmatter.platform.event.KeyAware
import software.darkmatter.platform.event.SchemaRegistry
import toughdevschool.ates.event.cud.user.v1.UserData
import toughdevschool.ates.event.cud.userRole.v1.UserRoleData
import kotlin.reflect.KClass
import toughdevschool.ates.event.cud.task.v1.TaskData as TaskDataV1
import toughdevschool.ates.event.cud.task.v2.TaskData as TaskDataV2

object CudEventSchemaRegistry : SchemaRegistry<CudEventType> {

    override val dataTypesMap: Map<Event.Type<CudEventType>, KClass<out KeyAware>> = mapOf(
        CudEventType.User.version(1) to UserData::class,
        CudEventType.UserRole.version(1) to UserRoleData::class,
        CudEventType.Task.version(1) to TaskDataV1::class,
        CudEventType.Task.version(2) to TaskDataV2::class,
    )

    override val eventTypeClass: KClass<CudEventType> = CudEventType::class

    fun CudEventType.version(version: Int) = Event.Type(this, version)
}
