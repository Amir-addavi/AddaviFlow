package com.Addavi.addaviflow.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextButton
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.Addavi.addaviflow.R
import com.Addavi.addaviflow.data.DataModel
import com.Addavi.addaviflow.data.DataModelRoot
import com.Addavi.addaviflow.data.uidata.ArzUiModel
import com.Addavi.addaviflow.ui.animations.LoadingShimmerAnimation
import com.Addavi.addaviflow.ui.components.ErrorPage
import com.Addavi.addaviflow.ui.components.LoadingGridBox
import com.Addavi.addaviflow.ui.theme.VazirFamily
import com.Addavi.addaviflow.viewmodel.FetchDataViewModel
import com.Addavi.addaviflow.viewmodel.Resource


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(arzViewModel : FetchDataViewModel = viewModel()){

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp


    val columns = (screenWidth / 180).coerceAtLeast(2)

    var isRefreshing by remember { mutableStateOf(false) }
    val shimmerBrush = LoadingShimmerAnimation()
    val arzData by arzViewModel.allItem.collectAsStateWithLifecycle()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing =true
            arzViewModel.FetchData()
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(horizontal = 17.dp)
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 25.sp,
                    fontFamily = VazirFamily,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = { arzViewModel.FetchData() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.refresh_ico),
                        contentDescription = "icon",
                        tint = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
            when (arzData) {
                is Resource.Loading -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columns),
                        modifier = Modifier
                            .padding(horizontal = 17.dp)
                            .padding(top = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(10) {
                            LoadingGridBox(gridState = rememberLazyGridState() , shimmerBrush)
                        }
                    }
                }
                is Resource.Error -> {
                    ErrorPage(R.drawable.error_ico , stringResource(R.string.error_connect) , stringResource(R.string.error_connect_dec) , stringResource(R.string.error_connect_btn)) { arzViewModel.FetchData() }
                }
                is Resource.Success->{
                    LaunchedEffect(arzData) {
                        when (arzData) {
                            is Resource.Loading -> isRefreshing = true
                            is Resource.Success,
                            is Resource.Error -> isRefreshing = false
                        }
                    }
                    val data = (arzData as Resource.Success<List<ArzUiModel>>).data
                    LazyVerticalGrid(
                        contentPadding = PaddingValues(bottom = 100.dp , top = 12.dp),
                        columns = GridCells.Fixed(columns),
                        modifier = Modifier
                            .padding(horizontal = 17.dp)
                            .padding(top = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(data){item->
                            ArzDataComponent(
                                pic = painterResource(item.type.icon),
                                fullname = stringResource(item.type.fulltitle),
                                name = stringResource(item.type.title),
                                oldprice = item.date,
                                price = item.price,
                                status = item.dt
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
fun ArzDataComponent(pic: Painter, fullname: String, name: String, oldprice: String, price: String , status: String){
    Box(
        modifier = Modifier
            .fillMaxWidth(0.48f)
            .aspectRatio(1.3f)
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
                        fontFamily = VazirFamily,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 1.sp
                    )
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.surface,
                        fontWeight = FontWeight.Medium,
                        fontFamily = VazirFamily,
                        lineHeight = 1.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(when (status){"low"-> R.drawable.down_arrow_ico "high"-> R.drawable.up_arrow_ico else-> R.drawable.nochange_ico}),
                        contentDescription = "icon",
                        modifier = Modifier
                            .width(14.dp)
                            .padding(end = 2.dp)
                    )
                    Text(
                        text = oldprice,
                        fontFamily = VazirFamily,
                        fontSize = 14.sp,
                        color = when (status){
                            "low"-> MaterialTheme.colorScheme.error
                            "high"->MaterialTheme.colorScheme.primary
                            else -> MaterialTheme.colorScheme.surface
                        },
                        lineHeight = 1.sp
                    )
                }
                Text(
                    text = price,
                    fontFamily = VazirFamily,
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}