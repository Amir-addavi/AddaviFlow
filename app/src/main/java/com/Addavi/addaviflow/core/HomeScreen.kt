package com.Addavi.addaviflow.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Addavi.addaviflow.R

@Composable
fun HomeScreen(){
    Column (
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = 17.dp)
                    .padding(top = 10.dp)
                ,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    ArzDataComponent(
                        1,
                        R.drawable.usa_ico,
                        "US Dollar",
                        "USD",
                        "4,000",
                        "108,550"
                    )
                }
                item {
                    ArzDataComponent(
                        2,
                        R.drawable.euro_ico,
                        "Euro",
                        "EURO",
                        "2,000",
                        "120,750"
                    )
                }
                item {
                    ArzDataComponent(
                        3,
                        R.drawable.uk_ico,
                        "British Pound",
                        "GBP",
                        "2,200",
                        "130,100"
                    )
                }
                item {
                    ArzDataComponent(
                        4,
                        R.drawable.bitc_ico,
                        "Bitcoin",
                        "BTC",
                        "1,000M",
                        "12,800B"
                    )
                }
        }
    }
}
@Composable
fun ArzDataComponent(id : Int , pic : Int ,fullname : String, name : String , oldprice : String , price : String){
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
                    painter = painterResource(pic),
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