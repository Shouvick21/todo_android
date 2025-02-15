package com.demo1.todo.core.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.demo1.todo.core.utils.ContentDescription
import com.demo1.todo.feature_todo.domain.utils.TodoItemsOrder

@Composable
fun CompleteBtn(
    onCompleteClick : () -> Unit,
    color: Color,
    completed: Boolean,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = {onCompleteClick()}
    ) {
        if(completed){
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = ContentDescription.COMPLETED_TODO_ITEM,
                tint = color,
                modifier = Modifier.size(48.dp)
            )
        }
        else{
            EmptyCircle(color = color)
        }
    }
}

@Composable
fun EmptyCircle(color: Color, strokeWidth : Float =9f) {
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            val radius = 39F
            drawCircle(
                color = color,
                radius = radius,
                center = center,
                style = Stroke(width = strokeWidth)
            )
        }
    )
}

@Composable
fun ArchiveBtn(
    onArchiveClick : () -> Unit,
    color: Color = MaterialTheme.colorScheme.secondary,
    modifier: Modifier =Modifier
) {
    IconButton(
        onClick = onArchiveClick,
    modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = ContentDescription.ARCHIVED_TODO_ITEM,
            tint = color,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun DeleteBtn(
    onDeleteClick : () -> Unit,
    modifier: Modifier =Modifier
) {
    IconButton(
        onClick = onDeleteClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = ContentDescription.DELETE_TODO_ITEM,
            modifier = Modifier.size(32.dp)
        )
    }
}