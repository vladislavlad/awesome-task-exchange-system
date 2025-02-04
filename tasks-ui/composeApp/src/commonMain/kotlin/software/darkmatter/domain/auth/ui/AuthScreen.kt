package software.darkmatter.domain.auth.ui

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
import software.darkmatter.domain.auth.repository.AuthRepository

@Composable
fun AuthScreen(authRepository: AuthRepository, navController: NavHostController) {

    val auth = authRepository.getCurrentAuth()

    if (auth == null) {
        Row {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = "Welcome to Task App", fontSize = 24.sp)
                Button(onClick = { navController.navigate("tasks") }) {
                    Text(text = "Login")
                }
            }
        }
    } else {
        navController.navigate("tasks")
    }
}
