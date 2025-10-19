package com.Addavi.addaviflow.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextButton
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.Addavi.addaviflow.R
import com.Addavi.addaviflow.data.ArzModel
import com.Addavi.addaviflow.data.remote.repository.Resource
import com.Addavi.addaviflow.ui.animations.LoadingShimmerAnimation
import com.Addavi.addaviflow.ui.components.LoadingGridBox
import com.Addavi.addaviflow.viewmodel.ArzViewModel

data class ManualArzData(
    val picResId: Int,
    val fullName: String,
    val name: String,
    val oldPrice: String
)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(arzViewModel: ArzViewModel = viewModel()){
    val manualDataList = listOf(
        ManualArzData(R.drawable.usa_ico, "US Dollar", "USD", "1,000"),
        ManualArzData(R.drawable.euro_ico, "Euro", "EUR", "1,330"),
        ManualArzData(R.drawable.uk_ico, "UK Pound", "GPB", "2,560"),
        ManualArzData(R.drawable.canada_ico, "Canada Dollar", "CAD", "1,960"),
        ManualArzData(R.drawable.turkey_ico, "Turkish Lira", "TRY", "960"),
        ManualArzData(R.drawable.uae_ico, "Emirates Dirham", "AED", "960")
    )
    val state by arzViewModel.state.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }
    val shimmerBrush = LoadingShimmerAnimation()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing =true
            arzViewModel.refresh()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(horizontal = 17.dp , vertical = 5.dp)
            ) {
                Text(
                    text = "Addavi Flow",
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            when (state) {
                is Resource.Loading -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .padding(horizontal = 17.dp)
                            .padding(top = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(6) {
                            LoadingGridBox(gridState = rememberLazyGridState() , shimmerBrush)
                        }
                    }
                }
                is Resource.Error -> {
                    Box(
                        modifier =
                            Modifier
                                .padding(vertical = 10.dp , horizontal = 17.dp)
                                .fillMaxSize()
                                .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Error connect to server" , color = MaterialTheme.colorScheme.surface ,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(vertical = 10.dp , horizontal = 17.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .border(width = 1.5.dp , color = MaterialTheme.colorScheme.error , shape = RoundedCornerShape(12.dp))
                                    .background(MaterialTheme.colorScheme.error.copy(alpha = 0.3f))
                                    .padding(vertical = 10.dp)
                                )
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                onClick = {arzViewModel.refresh()},
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.colorScheme.surface
                                ),
                            ) {
                                Text(
                                    text = "Retry",
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                is Resource.Succes -> {
                    LaunchedEffect(state) {
                        isRefreshing =false
                    }
                    val list =
                        (state as Resource.Succes<List<ArzModel>>).data.take(manualDataList.size)
                    val combinedlist =manualDataList.zip(list)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .padding(horizontal = 17.dp)
                            .padding(top = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(items = combinedlist , key = {it.second.id}) { (manualItem , apiItem)->
                            ArzDataComponent(
                                pic = painterResource(manualItem.picResId),
                                fullname = manualItem.fullName,
                                name = manualItem.name,
                                oldprice = manualItem.oldPrice,
                                price = apiItem.price
                            )
                        }
                    }
                }
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
@Composable
fun ArzDataComponent(pic: Painter, fullname: String, name: String, oldprice: String, price: String){
    Box(
        modifier = Modifier
            .fillMaxWidth(0.48f)
            .aspectRatio(1.4f)
            .clip(RoundedCornerShape(17.dp))
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 5.dp , horizontal = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = pic,
                    contentDescription = "Icons",
                    modifier = Modifier.width(30.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                    ,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = fullname,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 1.sp
                    )
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.surface,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 1.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column {
                Text(
                    text = oldprice,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 1.sp
                )
                Text(
                    text = price,
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}