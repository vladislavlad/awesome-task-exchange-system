package toughdevschool.ates.task.api

import org.springframework.data.domain.Pageable

object TaskAssignedListDto {

    data class Request(
        val pageable: Pageable
    )

    data class Response(
        val tasks: List<TaskDto.Response>
    )
}