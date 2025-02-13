package com.demo1.todo.feature_todo.domain.utils

sealed class SortingDirection {
    object up : SortingDirection()
    object down : SortingDirection()

}