package toughdevschool.ates.task.api.rest

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import toughdevschool.ates.task.api.TaskDto
import toughdevschool.ates.task.domain.task.business.TaskCrudApi
import javax.validation.Valid

@RestController
@RequestMapping("/tasks")
class TaskController(
    private val crudApi: TaskCrudApi
) {

    @GetMapping
    suspend fun list(
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("size", defaultValue = "20") size: Int,
    ) = crudApi.list(
        PageRequest.of(page, size)
    )

    @GetMapping(path = ["/{id}"])
    suspend fun get(@PathVariable id: Long) =
        crudApi.get(id)

    @PostMapping
    suspend fun create(@Valid @RequestBody body: TaskDto.CreateRequest) =
        crudApi.create(body)

    @PutMapping(path = ["/{id}"])
    suspend fun update(
        @PathVariable id: Long,
        @Valid @RequestBody body: TaskDto.UpdateRequest.Body,
    ) = crudApi.update(
        TaskDto.UpdateRequest(id, body)
    )
}
