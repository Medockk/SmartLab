package com.example.smartlab.test_composable_func

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R

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
        NavBar.Settings1,
        NavBar.Settings2,
        NavBar.Settings3,
    )
    ModalNavigationDrawer(
        modifier = Modifier.fillMaxWidth(),
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier.fillMaxWidth(0.7f)
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
        Scaffold(
            bottomBar = {
                NavigationBar {
                    listNavigBar.forEach{
                        NavigationBarItem(
                            selected = it.title == selectedIcon,
                            onClick = {
                                selectedIcon = it.title
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

        }
    }
}

sealed class NavBar(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : NavBar(
        "Home",
        "Home",
        R.drawable.test_ic_home
    )
    object Fav : NavBar(
        "Favorite",
        "Favorite",
        R.drawable.test_ic_fav
    )
    object Settings : NavBar(
        "Settings",
        "Settings",
        R.drawable.test_ic_settings
    )
    object Settings1 : NavBar(
        "Settings",
        "Settings",
        R.drawable.test_ic_settings
    )
    object Settings2 : NavBar(
        "Settings",
        "Settings",
        R.drawable.test_ic_settings
    )
    object Settings3 : NavBar(
        "Settings",
        "Settings",
        R.drawable.test_ic_settings
    )
}

sealed class Nav(val route: String){

}