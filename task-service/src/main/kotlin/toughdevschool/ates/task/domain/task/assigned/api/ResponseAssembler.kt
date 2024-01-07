package toughdevschool.ates.task.domain.task.assigned.api

import arrow.core.right
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.task.api.TaskAssignedListDto
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.assigned.business.TaskAssignedListResponse

@Component
class ResponseAssembler : ResponseAssembler<TaskAssignedListResponse, TaskAssignedListDto.Response> {

    override suspend fun assemble(business: TaskAssignedListResponse) =
        TaskAssignedListDto.Response(
            tasks = business.tasks.map {
                TaskDto.Response(
                    id = it.id!!,
                    uuid = it.uuid,
                    title = it.title,
                    description = it.description,
                    jiraId = it.jiraId,
                    status = it.status,
                    userUuid = it.userUuid,
                )
            }
        ).right()
}
