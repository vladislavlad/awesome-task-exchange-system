package toughdevschool.ates.task.domain.task.complete.api

import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.RequestAssembler
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.complete.business.TaskComplete
import toughdevschool.ates.task.domain.task.complete.business.TaskCompleteApi
import toughdevschool.ates.task.domain.task.complete.business.TaskCompleteService

@Component
class Api(
    override val requestAssembler: RequestAssembler<TaskDto.TaskCompleteRequest, TaskComplete>,
    override val service: TaskCompleteService,
    override val responseAssembler: ResponseAssembler<Unit, Unit>,
) : TaskCompleteApi()
