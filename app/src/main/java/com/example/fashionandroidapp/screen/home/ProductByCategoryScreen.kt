package com.example.fashionandroidapp.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fashionandroidapp.R
import com.example.fashionandroidapp.screen.home.ListProduct as ListProduct

//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    ProductByCategoryScreen("BasketBall")
//}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductByCategoryScreen(itemId: String, navController: NavController ) {
    Box(Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar()
        ListProduct(
            itemId = itemId,
            modifier = Modifier
                .fillMaxSize(),
            navController = navController
        )
    }
}

@Composable
fun ListProduct(itemId: String, modifier: Modifier,navController: NavController, viewModel: ProductViewModel = hiltViewModel()) {
    val products by viewModel.getProductsByCategory(itemId).collectAsState(initial = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(top = 14.dp, start = 6.dp, end = 6.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(top = 0.dp)
    ) {
        items(products.size) { product ->
            val productId = products[product].id
            Column() {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clickable {
                            navController.navigate("productDetailsScreen/$productId")
                        }
                ){
                    Box(modifier = Modifier
                        .padding(5.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(14.dp))) {
                        ImageFromAssets(
                            fileName = products[product].imageUrl,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
                Text(
                    text = products[product].name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Default,
                    modifier = Modifier
                        .padding(top = 4.dp, start = 1.dp)
                )
                Text(
                    text = "$" + products[product].price,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier
                        .padding(top = 4.dp, start = 1.dp)
                )
            }
        }
    }
}

