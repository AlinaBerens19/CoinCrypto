package com.example.coingechkoproject.presentation.components



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp

import com.example.mycrypto.R
import kotlinx.coroutines.launch


@Composable
fun SearchBar(url: String?) {

//    val scaffoldState = rememberScaffoldState(
//        rememberDrawerState(DrawerValue.Closed)
//    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                        Text(
                            text = "Your crypto",
                            color = Color.White)
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null // decorative element
                    )

                },
                backgroundColor = Color.Blue,

                contentColor = Color.White,

                elevation = 12.dp,


                actions = {
                    IconButton(onClick = { onFavoriteButtonPress() }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        },

        content = {
            ScrollingList(url)
            //ScrollingList_ViewModel()
                  },

        floatingActionButtonPosition = FabPosition.End,

        floatingActionButton = { MyFloatingButton() },

        isFloatingActionButtonDocked = true,
    )
}

//crash of floating button!!!!
@Composable
fun MyFloatingButton() {
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    FloatingActionButton(onClick = {
        scope.launch {
            scrollState.animateScrollToItem(0)
        }
    }) {
        Icon(Icons.Filled.KeyboardArrowUp, contentDescription = null)
    }
}

//show favorite crypto
fun onFavoriteButtonPress()
{

}
