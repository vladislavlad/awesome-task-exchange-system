package toughdevschool.ates.task.event.producer.cud

import software.darkmatter.event.cud.CudEvent
import toughdevschool.ates.task.Constants.SERVICE_NAME

abstract class TaskStreamEvent<Info, PublicId> : CudEvent<Info, PublicId> {

    override val source = SERVICE_NAME
}
