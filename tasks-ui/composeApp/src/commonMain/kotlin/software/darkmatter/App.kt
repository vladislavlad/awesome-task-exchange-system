package software.darkmatter

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import software.darkmatter.domain.auth.repository.AuthRepositoryStatic
import software.darkmatter.domain.auth.ui.AuthScreen
import software.darkmatter.domain.error.ui.Error404Screen
import software.darkmatter.domain.task.repository.TaskRepositoryStatic
import software.darkmatter.domain.task.ui.TaskListScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val authRepository = AuthRepositoryStatic()
        val taskRepository = TaskRepositoryStatic()

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "tasks") {
            composable("auth") { AuthScreen(authRepository, navController) }
            composable("tasks") { TaskListScreen(taskRepository, navController) }
            composable("error404") { Error404Screen(navController) }
//            composable("currentBalance") { CurrentBalanceScreen(accountingRepository) }
//            composable("transactionsList") { TransactionsListScreen(accountingRepository) }
//            composable("userMenu") { UserMenuScreen(accountsRepository) }
        }
    }
}
