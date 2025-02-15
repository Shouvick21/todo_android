package com.demo1.todo.feature_todo.presentation.todo_list.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.demo1.todo.R
import com.demo1.todo.core.utils.ContentDescription
import com.demo1.todo.core.utils.Todo_List_String_Resource
import com.demo1.todo.feature_todo.presentation.todo_list.TodoListEvent
import com.demo1.todo.feature_todo.presentation.todo_list.Todo_list_viewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun todoLIstScreenUi(
    navController: NavController,
    viewModel: Todo_list_viewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val snackberHostState = remember { SnackbarHostState() }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // it will check screen configaration that it is portrait or landscape
    val configaration = LocalConfiguration.current
    val isportrait =
        configaration.orientation == Configuration.ORIENTATION_PORTRAIT
    val backGroundImage =
        if (isportrait) R.drawable.background_portrait else R.drawable.background_landscape

    LaunchedEffect(key1 = true) {
        viewModel.getTodoItems()
    }

    ModalNavigationDrawer(

        drawerState = drawerState,
        drawerContent = {
            Box(modifier = Modifier.fillMaxWidth(0.65f)){
                ModalDrawerSheet {
                    Text(
                        text = Todo_List_String_Resource.SORT_BY,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 34.sp,
                        lineHeight = 38.sp
                    )
                    HorizontalDivider()
                    SortingDraweroptions(
                        todoItemsOrder = state.todoItemsOrder,
                        onOrderChange = { order ->
                            viewModel.onEvent(TodoListEvent.Sort(order))
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {},
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = ContentDescription.ADD,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )

                }
            },
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = Todo_List_String_Resource.TODO_LIST,
                            maxLines = 1,
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                        scrolledContainerColor = MaterialTheme.colorScheme.primary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    navigationIcon = {},
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Menu,
                                contentDescription = "menu"
                                )

                        }
                    },
                    scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState()),
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackberHostState) }
        ) {innerPadding ->
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier.fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
            ){
                Image(
                    painter = painterResource(backGroundImage),
                    contentDescription = "Backgroud image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.TopStart
                )
            }
            Column(
                modifier =  Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                        .padding(horizontal = 12.dp)
                        .padding(top = 64.dp)
                ) {
                    items(state.todoItems){todo ->
                        TodoItemCard(
                            todo = todo,
                            modifier = Modifier.fillMaxWidth()
                                .padding(4.dp),
                            onDeleteClick = {
                                viewModel.onEvent(TodoListEvent.Delete(todo))
                                scope.launch {
                                    val undo = snackberHostState.showSnackbar(
                                        message = Todo_List_String_Resource.TODO_ITEM_DELETED,
                                        actionLabel = Todo_List_String_Resource.UNDO,
                                    )
                                    if(undo == SnackbarResult.ActionPerformed){
                                        viewModel.onEvent(TodoListEvent.undoDelete)
                                    }
                                }
                            },
                            onCompleteClick = {
                                viewModel.onEvent(TodoListEvent.ToggleCompleted(todo))
                            },
                            onArchiveClick = {
                                viewModel.onEvent(TodoListEvent.ToogleArchived(todo))

                            },
                            onCardClick = {
//                                navController.navigate()
                            }
                        )
                    }
                }
            }
            if (state.loading){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        Modifier.semantics {
                            this.contentDescription =ContentDescription.LOADING_INDECATOR
                        }
                    )
                }
            }
            else if (state.error != null){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.error,
                        fontSize = 30.sp,
                        lineHeight = 36.sp
                    )
                }
            }

        }

    }


}