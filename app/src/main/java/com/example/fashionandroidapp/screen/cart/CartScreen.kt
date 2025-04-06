package com.example.fashionandroidapp.screen.cart

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fashionandroidapp.R
import com.example.fashionandroidapp.data.model.Product
import com.example.fashionandroidapp.screen.home.ImageFromAssets
import com.example.fashionandroidapp.screen.home.ProductViewModel
import com.example.fashionandroidapp.ui.theme.background_Color_2
import com.example.fashionandroidapp.ui.theme.icon_Color

//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    CartScreen1()
//}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen (navController: NavController, cartViewModel: CartViewModel = hiltViewModel(), productViewModel: ProductViewModel = hiltViewModel()) {
    val cartProducts by cartViewModel.cartProducts.observeAsState(emptyList())
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Text(
            text = "Cart",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(start = 20.dp, top = 10.dp)
        )
        LazyColumn(
            modifier = Modifier.
            padding(start = 20.dp, end = 20.dp, top = 20.dp ),
            verticalArrangement = Arrangement.spacedBy(14.dp)

        ) {
            items(cartProducts.size) { cartProduct ->
                //val product = productViewModel.getProductById(cartProducts[cartProduct].id)
                var product by remember { mutableStateOf<Product?>(null) }
                val productId = cartProducts[cartProduct].productId
                LaunchedEffect(productId) {
                    product = productViewModel.getProductById(productId)
                }
                product?.let {
                    CardProductAddedToCart(
                        quantity = cartProducts[cartProduct].quantity.toString(),
                        imageUrl = it.imageUrl,
                        name = it.name,
                        price = it.price.toString(),
                        onLessClick = {
                            cartViewModel.checkAndDecreaseProductToCart(productId)
                                      },
                        onMoreClick = {
                            cartViewModel.checkAndIncreaseProductToCart(productId)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CardProductAddedToCart(quantity :String, imageUrl: String, name: String, price: String, onLessClick: () -> Unit, onMoreClick: () -> Unit){
    Row(){
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .size(150.dp)
                .aspectRatio(1f)
                .clickable {
                }
        ){
            Box(modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(14.dp))) {
                ImageFromAssets(
                    fileName = imageUrl,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Default,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
            )

            Text(
                text = price,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier
                    .padding(top = 15.dp, start = 10.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                IconCustom(
                    id = R.drawable.less,
                    onClick = {
                        onLessClick()
                    }
                )

                Card(
                    shape = RoundedCornerShape(2.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = background_Color_2
                    ),
                    modifier = Modifier
                        .size(width = 37.dp, height = 30.dp)

                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = quantity,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }

                IconCustom(
                    id = R.drawable.more,
                    onClick = {
                        onMoreClick()
                    }
                )
            }
        }
    }
}

@Composable
fun IconCustom(id: Int, onClick: () -> Unit) {
    Icon(
        modifier = Modifier
            .size(40.dp, 40.dp)
            .clickable(onClick = onClick),
        painter = painterResource(id = id),
        contentDescription = "Home",
        tint = icon_Color
    )
}
