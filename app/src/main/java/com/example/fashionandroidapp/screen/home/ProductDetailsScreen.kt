package com.example.fashionandroidapp.screen.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fashionandroidapp.R
import com.example.fashionandroidapp.data.model.Product
import com.example.fashionandroidapp.screen.cart.CartViewModel
import com.example.fashionandroidapp.ui.theme.icon_Color

//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    ProductDetailsScreen("1")
//}

@Composable
fun ProductDetailsScreen(id: String, navController: NavController, productViewModel: ProductViewModel = hiltViewModel(), cartViewModel: CartViewModel = hiltViewModel() ) {
    val productId = id.toInt()
    var product by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(productId) {
        product = productViewModel.getProductById(productId)
    }
    Box(modifier = Modifier
        .fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            product?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(375f / 439f)
                ) {

                    ImageFromAssets(
                        fileName = it.imageUrl,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }


                Text(
                    text = "$" + it.price,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier
                        .padding(top = 15.dp, start = 10.dp)
                )
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam arcu mauris, scelerisque eu mauris id, pretium pulvinar sapien.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Default,
                    modifier = Modifier
                        .padding(top = 5.dp, start = 10.dp)
                )

                Text(
                    text = "Color Options",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    modifier = Modifier
                        .padding(top = 15.dp, start = 10.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

            }

            Text(
                text = "Size",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                modifier = Modifier
                    .padding(top = 15.dp, start = 10.dp)
            )


        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(70.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(
                        height = 40.dp,
                        width = 128.dp
                    )
                    .clickable {
                        cartViewModel.checkAndIncreaseProductToCart(productId)
                    }
            ){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Add to cart",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                        color = Color.White
                    )
                }

            }

            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = icon_Color
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(
                        height = 40.dp,
                        width = 128.dp
                    )
                    .clickable {

                    }
            ){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Buy Now",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                        color = Color.White
                    )
                }

            }
        }
    }
}