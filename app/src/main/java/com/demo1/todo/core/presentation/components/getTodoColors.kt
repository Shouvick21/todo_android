package com.demo1.todo.core.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.demo1.todo.feature_todo.domain.model.TodoModel

data class TodoItemColors(
    val backgroundColor : Color,
    val textColor : Color,
    val archivedColor: Color,
    val checkColor : Color
)

@Composable
fun getTodoColors(todo : TodoModel) : TodoItemColors {
    return if(todo.archived){
        TodoItemColors(
            backgroundColor = MaterialTheme.colorScheme.secondary.copy(
                alpha = 0.6f
            ),
            textColor = MaterialTheme.colorScheme.onSecondary,
            archivedColor = MaterialTheme.colorScheme.onSecondary,
            checkColor = if(todo.isCompleted) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSecondary
        )
    }
    else{
        TodoItemColors(
            backgroundColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.6f
            ),
            textColor = MaterialTheme.colorScheme.onPrimary,
            archivedColor = MaterialTheme.colorScheme.onSecondary,
            checkColor = if(todo.isCompleted) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
        )
    }
}