package software.darkmatter.domain.error.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Error404Screen(navController: NavHostController) {
    Row {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Page not found", fontSize = 24.sp)
            Button(onClick = { navController.navigate("tasks") }) {
                Text(text = "Go to home")
            }
        }
    }
}
