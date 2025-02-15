package com.demo1.todo.feature_todo.presentation.todo_list.components

import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import com.demo1.todo.core.utils.Todo_List_String_Resource
import com.demo1.todo.feature_todo.domain.utils.SortingDirection
import com.demo1.todo.feature_todo.domain.utils.TodoItemsOrder

@Composable
fun SortingDraweroptions(
    todoItemsOrder: TodoItemsOrder,
    onOrderChange : (TodoItemsOrder) -> Unit
) {
    val titleSelected = todoItemsOrder::class == TodoItemsOrder.Title::class
    NavigationDrawerItem(
        badge = {},
        selected = false,
        label = {
            IconRow(
                text = Todo_List_String_Resource.TITLE,
                isChecked = titleSelected
            )
        },
        onClick = {onOrderChange(TodoItemsOrder.Title(todoItemsOrder.sortingDirection,todoItemsOrder.showArchived))},
    )

    val timeSelected = todoItemsOrder::class == TodoItemsOrder.Time::class
    NavigationDrawerItem(
        badge = {},
        selected = false,
        label = {
            IconRow(
                text = Todo_List_String_Resource.TIME,
                isChecked = timeSelected
            )
        },
        onClick = {onOrderChange(TodoItemsOrder.Time(todoItemsOrder.sortingDirection,todoItemsOrder.showArchived))},
    )
    val completedSelected = todoItemsOrder::class == TodoItemsOrder.Completed::class
    NavigationDrawerItem(
        badge = {},
        selected = false,
        label = {
            IconRow(
                text = Todo_List_String_Resource.COMPLETED,
                isChecked = completedSelected
            )
        },
        onClick = {onOrderChange(TodoItemsOrder.Completed(todoItemsOrder.sortingDirection,todoItemsOrder.showArchived))},
    )
    HorizontalDivider()
    val sortDownSelected = todoItemsOrder.sortingDirection == SortingDirection.down
    NavigationDrawerItem(
        badge = {},
        selected = false,
        label = {
            IconRow(
                text = Todo_List_String_Resource.SORT_DOWN,
                isChecked = sortDownSelected
            )
        },
        onClick = {onOrderChange(todoItemsOrder.copy(sortingDirection = SortingDirection.down, showArchived = todoItemsOrder.showArchived ))},
    )
    val sortUpSelected = todoItemsOrder.sortingDirection == SortingDirection.up
    NavigationDrawerItem(
        badge = {},
        selected = false,
        label = {
            IconRow(
                text = Todo_List_String_Resource.SORT_UP,
                isChecked = sortUpSelected
            )
        },
        onClick = {onOrderChange(todoItemsOrder.copy(sortingDirection = SortingDirection.up , showArchived = todoItemsOrder.showArchived))},
    )
    HorizontalDivider()
    NavigationDrawerItem(
        badge = {},
        selected = false,
        label = {
            IconRow(
                text = Todo_List_String_Resource.SHOW_ARCHIVE,
                isChecked = todoItemsOrder.showArchived
            )
        },
        onClick = {onOrderChange(todoItemsOrder.copy(sortingDirection = todoItemsOrder.sortingDirection , showArchived = !todoItemsOrder.showArchived))},
    )

}