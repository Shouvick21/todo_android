package com.demo1.todo.feature_todo.data.mapper

import com.demo1.todo.feature_todo.data.local.dto.LocalTodoModel
import com.demo1.todo.feature_todo.data.remote.dto.RemoteTodoModel
import com.demo1.todo.feature_todo.domain.model.TodoModel

fun TodoModel.toLocalTodomodel() : LocalTodoModel {
    return LocalTodoModel(
        id = id,
        title = title,
        desciption = desciption,
        archived = archived,
        timeStamp = timeStamp,
        isCompleted = isCompleted,
    )
}

fun TodoModel.toRemoteTodomodel() : RemoteTodoModel {
    return RemoteTodoModel(
        id = id,
        title = title,
        desciption = desciption,
        archived = archived,
        timeStamp = timeStamp,
        isCompleted = isCompleted,
    )
}

fun LocalTodoModel.toTodomodel() : TodoModel {
    return TodoModel(
        id = id,
        title = title,
        desciption = desciption,
        archived = archived,
        timeStamp = timeStamp,
        isCompleted = isCompleted,
    )
}

fun LocalTodoModel.toRemoteTodomodel() : RemoteTodoModel {
    return RemoteTodoModel(
        id = id,
        title = title,
        desciption = desciption,
        archived = archived,
        timeStamp = timeStamp,
        isCompleted = isCompleted,
    )
}

fun RemoteTodoModel.toTodomodel() : TodoModel {
    return TodoModel(
        id = id,
        title = title,
        desciption = desciption,
        archived = archived,
        timeStamp = timeStamp,
        isCompleted = isCompleted,
    )
}

fun RemoteTodoModel.toLocalTodomodel() : LocalTodoModel {
    return LocalTodoModel(
        id = id,
        title = title,
        desciption = desciption,
        archived = archived,
        timeStamp = timeStamp,
        isCompleted = isCompleted,
    )
}

fun List<TodoModel>.toListOfLocalTodoModel() : List<LocalTodoModel> {
    return this.map {
        LocalTodoModel(
            id = it.id,
            title = it.title,
            desciption = it.desciption,
            archived = it.archived,
            timeStamp = it.timeStamp,
            isCompleted = it.isCompleted,
        )
    }
}

fun List<TodoModel>.toListOfRemoteTodoModel() : List<RemoteTodoModel> {
    return this.map {
        RemoteTodoModel(
            id = it.id,
            title = it.title,
            desciption = it.desciption,
            archived = it.archived,
            timeStamp = it.timeStamp,
            isCompleted = it.isCompleted,
        )
    }
}

fun List<RemoteTodoModel>.toListOfTodoModelFromRemote() : List<TodoModel> {
    return this.map {
        TodoModel(
            id = it.id,
            title = it.title,
            desciption = it.desciption,
            archived = it.archived,
            timeStamp = it.timeStamp,
            isCompleted = it.isCompleted,
        )
    }
}

fun List<RemoteTodoModel>.toListOfLocalTodoModelFromRemote() : List<LocalTodoModel> {
    return this.map {
        LocalTodoModel(
            id = it.id,
            title = it.title,
            desciption = it.desciption,
            archived = it.archived,
            timeStamp = it.timeStamp,
            isCompleted = it.isCompleted,
        )
    }
}

fun List<LocalTodoModel>.toListOfTodoModelFromLocal() : List<TodoModel> {
    return this.map {
        TodoModel(
            id = it.id,
            title = it.title,
            desciption = it.desciption,
            archived = it.archived,
            timeStamp = it.timeStamp,
            isCompleted = it.isCompleted,
        )
    }
}

fun List<LocalTodoModel>.toListOfRemoteTodoModelFromLocal() : List<RemoteTodoModel> {
    return this.map {
        RemoteTodoModel(
            id = it.id,
            title = it.title,
            desciption = it.desciption,
            archived = it.archived,
            timeStamp = it.timeStamp,
            isCompleted = it.isCompleted,
        )
    }
}

