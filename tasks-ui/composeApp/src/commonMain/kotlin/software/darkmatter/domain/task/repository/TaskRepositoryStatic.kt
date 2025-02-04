package software.darkmatter.domain.task.repository

import software.darkmatter.domain.task.Task
import kotlin.uuid.Uuid

class TaskRepositoryStatic : TaskRepository {

    override fun getTasks(): List<Task> {
        return listOf(
            Task(1, Uuid.random(), Uuid.random(), "Task 1", "Description 1", Task.Status.New),
            Task(2, Uuid.random(), Uuid.random(), "Task 2", "Description 2", Task.Status.Completed),
        )
    }

    override fun completeTask(id: Long) {
        // Mark task as complete
    }
}
