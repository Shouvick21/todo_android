package com.demo1.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.demo1.todo.feature_todo.presentation.Screens
import com.demo1.todo.feature_todo.presentation.todo_list.Todo_list_viewModel
import com.demo1.todo.feature_todo.presentation.todo_list.components.todoLIstScreenUi
import com.demo1.todo.ui.theme.TodoTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTheme {
                val navController = rememberNavController()
                val listViewmodel : Todo_list_viewModel = hiltViewModel()

                NavHost(
                    navController = navController,
                    startDestination = Screens.TodoListScreen.route
                ) {
                    composable(Screens.TodoListScreen.route){
                        todoLIstScreenUi(navController = navController, viewModel = listViewmodel)
                    }
                    composable(Screens.TodoUpdateScreen.route + "?todoId = {todoId}",

                        arguments = listOf(
                            navArgument(
                                name = "todoId"
                            ){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){
                        // TODO

                    }
                }

            }
        }
    }
}

