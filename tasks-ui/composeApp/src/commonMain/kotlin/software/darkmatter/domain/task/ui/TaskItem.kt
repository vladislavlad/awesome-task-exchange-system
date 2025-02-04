package software.darkmatter.domain.task.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import software.darkmatter.domain.task.Task

@Composable
fun TaskItem(task: Task) {

    val taskStatus = remember { mutableStateOf(task.status) }

    Card(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 12.dp))
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                Text("Description: ")
                Text(text = task.description)
            }
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                Text("Status: ")
                when (taskStatus.value) {
                    Task.Status.New -> AssistChip(
                        onClick = {},
                        label = { Text("New") },
                        modifier = Modifier.height(24.dp),
                    )
                    Task.Status.Completed -> AssistChip(
                        onClick = {},
                        label = { Text("Completed") },
                        modifier = Modifier.height(24.dp),
                        colors = AssistChipDefaults.assistChipColors(
                            labelColor = Color(red = 100, green = 200, blue = 100),
                        ),
                    )
                }
            }
            when (taskStatus.value) {
                Task.Status.Completed -> Button(onClick = { taskStatus.setValue(task, task::status, Task.Status.New) }) {
                    Text("Undo")
                }
                Task.Status.New -> Button(
                    onClick = { taskStatus.setValue(task, task::status, Task.Status.Completed) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(red = 100, green = 200, blue = 100))
                ) {
                    Text("Complete")
                }
            }
        }
    }
}
