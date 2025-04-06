package com.example.fashionandroidapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fashionandroidapp.R
import com.example.fashionandroidapp.screen.cart.CartScreen
import com.example.fashionandroidapp.screen.categories.CategoriesScreen
import com.example.fashionandroidapp.screen.favorite.FavoritesScreen
import com.example.fashionandroidapp.screen.home.HomeScreen
import com.example.fashionandroidapp.screen.home.ProductByCategoryScreen
import com.example.fashionandroidapp.screen.home.ProductDetailsScreen
import com.example.fashionandroidapp.screen.profile.ProfileScreen
import com.example.fashionandroidapp.ui.theme.icon_Color


@Preview(showBackground = true)
@Composable
fun Preview() {
    MainScreen()
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
                BottomAppBar(
                    modifier = Modifier.height(84.dp)
                        .shadow(elevation = 20.dp, shape = RectangleShape)
                        .background(Color.White),
                    containerColor = Color.Transparent,
                ) {
                    val isEnabled by remember { mutableStateOf(false) }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconCustom(id = R.drawable.appbar_homeenable,
                            isEnabled = (currentRoute == "homeScreen" || currentRoute == "productByCategoryScreen/{itemId}"),
                            onClick = { navController.navigate("homeScreen") })
                        IconCustom(
                            id = R.drawable.appbar_favoriteenable,
                            isEnabled = (currentRoute == "favoritesScreen"),
                            onClick = { navController.navigate("favoritesScreen") })
                        IconCustom(
                            id = R.drawable.appbar_categoriesenable,
                            isEnabled = (currentRoute == "categoriesScreen"),
                            onClick = { navController.navigate("categoriesScreen") })
                        IconCustom(
                            id = R.drawable.appbar_cartenable,
                            isEnabled = (currentRoute == "cartScreen"),
                            onClick = { navController.navigate("cartScreen") })
                        IconCustom(
                            id = R.drawable.appbar_profileenable,
                            isEnabled = (currentRoute == "profileScreen"),
                            onClick = { navController.navigate("profileScreen") })
                    }
                }
        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = "homeScreen", modifier = Modifier.padding(paddingValues)) {
            composable("homeScreen") { HomeScreen(navController) }
            composable("favoritesScreen") { FavoritesScreen(navController) }
            composable("categoriesScreen") { CategoriesScreen(navController) }
            composable("cartScreen") { CartScreen(navController) }
            composable("profileScreen") { ProfileScreen(navController) }
            composable("productByCategoryScreen/{itemId}") { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId") ?: "Unknown"
                ProductByCategoryScreen(itemId, navController)
            }
            composable("productDetailsScreen/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId") ?: "Unknown"
                ProductDetailsScreen(productId, navController)
            }

        }
    }
}

@Composable
fun IconCustom(id: Int, isEnabled: Boolean, onClick: () -> Unit) {
    Icon(
        modifier = Modifier
            .size(40.dp, 40.dp)
            .clickable(onClick = onClick),
        painter = painterResource(id = handleIcon(id, isEnabled)),
        contentDescription = "Home",
        tint = if (isEnabled) Color.Black else icon_Color
    )
}

fun handleIcon(id: Int, isEnabled: Boolean): Int {
    return when (id) {
        R.drawable.appbar_homeenable -> (if (isEnabled) R.drawable.appbar_homeenable else R.drawable.appbar_homedisable)
        R.drawable.appbar_favoriteenable -> (if (isEnabled) R.drawable.appbar_favoriteenable else R.drawable.appbar_favoritedisable)
        R.drawable.appbar_categoriesenable -> (if (isEnabled) R.drawable.appbar_categoriesenable else R.drawable.appbar_categoriesdisable)
        R.drawable.appbar_cartenable -> (if (isEnabled) R.drawable.appbar_cartenable else R.drawable.appbar_cartdisable)
        R.drawable.appbar_profileenable -> (if (isEnabled) R.drawable.appbar_profileenable else R.drawable.appbar_profiledisable)
        else -> R.drawable.appbar_homeenable
    }
}