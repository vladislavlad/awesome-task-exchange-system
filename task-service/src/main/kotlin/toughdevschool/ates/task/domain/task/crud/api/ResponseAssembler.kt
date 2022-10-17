package toughdevschool.ates.task.domain.task.crud.api

import arrow.core.right
import org.springframework.stereotype.Component
import software.darkmatter.platform.assembler.ResponseAssembler
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.data.Task

@Component
class ResponseAssembler : ResponseAssembler<Task, TaskDto.Response> {

    override suspend fun assemble(business: Task) =
        with(business) {
            TaskDto.Response(
                id = id!!,
                uuid = uuid,
                title = title,
                description = description,
                jiraId = jiraId,
                status = status,
                userId = userId,
            )
        }.right()
}
