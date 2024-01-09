package toughdevschool.ates.task.api

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.domain.Pageable

object TaskAssignedListDto {

    data class Request(
        val pageable: Pageable
    )

    @Schema(name = "TaskAssignedListResponse")
    data class Response(
        val tasks: List<TaskDto.Response>
    )
}