package com.example.fashionandroidapp.screen.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.fashionandroidapp.R
import com.example.fashionandroidapp.ui.theme.background_Color
import com.example.fashionandroidapp.ui.theme.icon_Color
import kotlinx.coroutines.delay

//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    HomeScreen()
//}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        SearchBar()
        SwipeViewWithIndicator(
            modifier = Modifier
                .height(250.dp)
                .padding(10.dp)
        )

        LazyGridCategories(navController)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 10.dp)
    ) {
        var text by remember { mutableStateOf("") }
        Text(
            text = "Shop",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(start = 10.dp)
        )
        var textFieldHeight by remember { mutableStateOf(0) }
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Search",
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Serif
                )
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier
                        .size(20.dp, 20.dp),
                    //.clickable(onClick = onClick),
                    painter = painterResource(id = R.drawable.appsearch_image_icon),
                    contentDescription = "Home",
                    tint = Color.Black
                )
            },
            singleLine = true,
            modifier = Modifier
                .onSizeChanged { textFieldHeight = it.height }
                .padding(end = 10.dp)
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(textFieldHeight / 2))
                .clip(RoundedCornerShape(textFieldHeight / 2)),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )

        )
    }
}

@Composable
fun LazyGridCategories(navController: NavController ,viewModel: ProductViewModel = hiltViewModel()) {
    val categories by viewModel.getAllCategories().collectAsState(initial = emptyList())
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 6.dp, end = 6.dp, top = 6.dp, bottom = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(categories.size) { category ->
            val categoryName = categories[category]
            val productCount by viewModel.countProductsByCategory(categoryName).observeAsState(0)
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(165f / 192f)
                    .clickable {
                        navController.navigate("productByCategoryScreen/$categoryName")
                    }
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val products by viewModel.get4ProductsByCategory(categoryName)
                        .collectAsState(initial = emptyList())
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // Grid con: 2 cột
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(
                            start = 5.dp,
                            top = 5.dp,
                            end = 5.dp,
                            bottom = 5.dp
                        ),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(products.size) { product ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f / 1f),
                                //.background(Color.Blue, shape = RoundedCornerShape(6.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                ImageFromAssets(
                                    fileName = products[product].imageUrl,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(6.dp))
                                )
                            }
                        }
                    }
                    Text(
                        text = categoryName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 5.dp, bottom = 4.dp)
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 6.dp, bottom = 6.dp)
                            .background(color = background_Color, shape = RoundedCornerShape(6.dp))
                    ) {
                        Text(
                            text = productCount.toString(),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier
                                .padding(2.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun SwipeViewWithIndicator(modifier: Modifier, viewModel: BannerAdvertisementViewModel= hiltViewModel()) {
    val bannerAdvertisements by viewModel.getAllBannerAdvertisements().collectAsState(initial = emptyList())
    val pagerState = rememberPagerState(initialPage = 0) { bannerAdvertisements.size }
    val interactionSource = remember { MutableInteractionSource() }

    // Check if user is interacting (scrolling/touching)
    val isUserInteracting by interactionSource.collectIsDraggedAsState()
    // Auto-switch pages using LaunchedEffect
    LaunchedEffect(Unit) {
        while (!isUserInteracting) {
            delay(4000) // Switch every 3 seconds
            val nextPage = (pagerState.currentPage + 1) % bannerAdvertisements.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            ImageFromAssets(
                fileName = bannerAdvertisements[page].imageUrl,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
            )
        }
        // Custom Indicator
        CustomPagerIndicator(
            pageCount = bannerAdvertisements.size,
            currentPage = pagerState.currentPage
        )
    }
}

@Composable
fun CustomPagerIndicator(
    pageCount: Int,
    currentPage: Int
) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .size(if (index == currentPage) 13.dp else 10.dp)
                    .background(
                        color = if (index == currentPage) icon_Color else Color.Gray,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun RoundedImageWithShadow(url: String, modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = url,
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ImageFromAssets(fileName: String, modifier: Modifier) {
    val context: Context = LocalContext.current
    val assetManager = context.assets
    val inputStream = assetManager.open(fileName)
    val bitmap = BitmapFactory.decodeStream(inputStream)
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            bitmap = bitmap.asImageBitmap(), contentDescription = "Image from Assets",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}