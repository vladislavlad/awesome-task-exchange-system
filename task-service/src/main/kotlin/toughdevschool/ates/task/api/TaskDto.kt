package toughdevschool.ates.task.api

import toughdevschool.ates.task.domain.task.data.Task
import java.util.UUID
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

object TaskDto {

    data class Response(
        var id: Long,
        val uuid: UUID,
        var title: String,
        var description: String,
        var status: Task.Status,
        var userId: Long,
    )

    data class CreateRequest(
        @field:NotEmpty
        var title: String?,
        @field:NotNull
        var description: String?,
    )

    data class UpdateRequest(
        var taskId: Long,
        var body: Body,
    ) {

        data class Body(
            @field:NotEmpty
            var title: String?,
            @field:NotNull
            var description: String?,
            var status: Task.Status,
        )
    }
}
