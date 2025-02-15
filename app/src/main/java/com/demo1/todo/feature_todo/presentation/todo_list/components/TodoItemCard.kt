package com.demo1.todo.feature_todo.presentation.todo_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo1.todo.core.presentation.components.ArchiveBtn
import com.demo1.todo.core.presentation.components.CompleteBtn
import com.demo1.todo.core.presentation.components.DeleteBtn
import com.demo1.todo.core.presentation.components.TodoItemColors
import com.demo1.todo.core.presentation.components.getTodoColors
import com.demo1.todo.feature_todo.domain.model.TodoModel

@Composable
fun TodoItemCard(
    todo: TodoModel,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onCompleteClick: () -> Unit,
    onArchiveClick: () -> Unit,
    onCardClick: () -> Unit,
) {
    val todoClors = getTodoColors(todo = todo)
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onCardClick,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(contentColor = todoClors.backgroundColor)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            CompleteBtn(
                onCompleteClick = onCompleteClick,
                color = todoClors.checkColor,
                completed = todo.isCompleted
            )
            Text(
                text = todo.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = todoClors.textColor,
                fontSize = 32.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = todo.desciption,
                    style = MaterialTheme.typography.bodyLarge,
                    color = todoClors.textColor,
                    fontSize = 24.sp,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
                    .padding(4.dp)
            ) {
                ArchiveBtn(
                    onArchiveClick = onArchiveClick,
                    color = todoClors.archivedColor
                )
                DeleteBtn(
                    onDeleteClick = onDeleteClick,
                )

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun previewTodoItemCard() {
    TodoItemCard(
        todo = TodoModel(
            title = "Plese Dont subscribe",
            desciption = "Keep learning jetpack compose",
            isCompleted = true,
            archived = true,
            id = 0,
            timeStamp = 12223434
        ),
        onDeleteClick = {},
        onCardClick = {},
        onArchiveClick = {},
        onCompleteClick = {}
    )
}