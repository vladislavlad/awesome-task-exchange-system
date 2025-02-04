package software.darkmatter.domain.task.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import software.darkmatter.domain.task.repository.TaskRepository

@Composable
fun TaskListScreen(taskRepository: TaskRepository, navController: NavHostController) {

    val tasks = taskRepository.getTasks()

    Column {
        Row(modifier = Modifier.padding(8.dp)) {
            Button(onClick = { navController.navigate("auth") }, modifier = Modifier.padding(start = 8.dp)) {
                Text("Auth")
            }
            Button(onClick = { navController.navigate("tasks") }, modifier = Modifier.padding(start = 8.dp)) {
                Text("Tasks")
            }
            Button(onClick = { navController.navigate("error404") }, modifier = Modifier.padding(start = 8.dp)) {
                Text("404 Page")
            }
        }

        LazyColumn {
            items(tasks) { task ->
                TaskItem(task)
            }
        }
    }
}
