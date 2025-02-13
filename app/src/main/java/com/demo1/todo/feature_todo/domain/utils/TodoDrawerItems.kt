package com.demo1.todo.feature_todo.domain.utils

sealed class TodoItemsOrder (
    val sortingDirection: SortingDirection,
    val showArchived : Boolean,
){
    class Title(sortingDirection: SortingDirection, showArchived: Boolean) : TodoItemsOrder(sortingDirection,showArchived)
    class Time(sortingDirection: SortingDirection, showArchived: Boolean) : TodoItemsOrder(sortingDirection,showArchived)
    class Completed(sortingDirection: SortingDirection, showArchived: Boolean) : TodoItemsOrder(sortingDirection,showArchived)
    fun copy(sortingDirection: SortingDirection,showArchived: Boolean) : TodoItemsOrder{
        return when(this){
            is Title -> Title(sortingDirection,showArchived)
            is Time -> Time(sortingDirection,showArchived)
            is Completed -> Completed(sortingDirection,showArchived)
        }
    }
}