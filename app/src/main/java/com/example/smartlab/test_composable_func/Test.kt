package com.example.smartlab.test_composable_func

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import kotlinx.coroutines.launch

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
Test(navController = rememberNavController())
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Test(navController: NavController) {
    val drawerState = remember { DrawerState(DrawerValue.Closed) }
    val listNavigBar = listOf(
        NavBar.Home,
        NavBar.Fav,
        NavBar.Settings,
    )
    val pagerState = rememberPagerState(
        pageCount = { listNavigBar.size },
    )
    ModalNavigationDrawer(
        modifier = Modifier.fillMaxWidth(),
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight()
                    .background(Color.Gray),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "profile",
                    tint = Color.Blue
                )
                Text("Profile")
            }
        },
        scrimColor = Color.Transparent
    ) {
        var selectedIcon by remember { mutableStateOf(NavBar.Home.title) }
        val coroutineScope = rememberCoroutineScope()
        Scaffold(
            bottomBar = {
                NavigationBar {
                    listNavigBar.forEach{
                        NavigationBarItem(
                            selected = it.title == selectedIcon,
                            onClick = {
                                selectedIcon = it.title
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(
                                        page = it.page
                                    )
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(it.icon),
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(it.title)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = com.example.smartlab.ui.theme.Test,
                                indicatorColor = Color.Transparent,
                                selectedTextColor = com.example.smartlab.ui.theme.Test
                            )
                        )
                    }
                }
            }
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
                    .padding(it)
            ) { page ->
                Text(text = listNavigBar[page].title)
            }
        }
    }
}

sealed class NavBar(
    val route: String,
    val title: String,
    val icon: Int,
    val page: Int,
) {
    object Home : NavBar(
        "Home",
        "Home",
        R.drawable.test_ic_home,
        page = 0
    )
    object Fav : NavBar(
        "Favorite",
        "Favorite",
        R.drawable.test_ic_fav,
        page = 1,
    )
    object Settings : NavBar(
        "Settings",
        "Settings",
        R.drawable.test_ic_settings,
        page = 2
    )
}

sealed class Nav(val route: String){

}