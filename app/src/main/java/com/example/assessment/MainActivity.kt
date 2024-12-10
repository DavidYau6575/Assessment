package com.example.assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.assessment.model.Item
import com.example.assessment.viewmodel.ItemViewModel

class MainActivity : ComponentActivity() {
    private val itemViewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                ShowItem(itemViewModel)
            }
        }
    }
}

@Composable
fun ShowItem(itemViewModel: ItemViewModel) {
    val lazyPagingItems: LazyPagingItems<Item> = itemViewModel.pagedItems.collectAsLazyPagingItems()
    val defaultSize = 20.sp

    when (lazyPagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            Text("Loading . . . ", fontSize = defaultSize)
        }

        is LoadState.Error -> {
            Text("Error occurred", fontSize = defaultSize)
        }

        else -> {
            LazyColumn(
                horizontalAlignment = Alignment.Start,
            ) {
                items(count = lazyPagingItems.itemCount) { index ->
                    val item = lazyPagingItems[index]
                    item?.let {
                        Text(text = item.transactionId, fontSize = defaultSize)
                        Text(text = item.stockSymbol, fontSize = defaultSize)
                        Text(text = item.buySell, fontSize = defaultSize)
                        Text(text = item.quantity.toString(), fontSize = defaultSize)
                        Text(text = item.price.toString(), fontSize = defaultSize)
                        Text(text = item.timestamp, fontSize = defaultSize)
                        Text(text = item.orderType, fontSize = defaultSize)
                        if (index != lazyPagingItems.itemCount - 1) {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 16.dp),
                                thickness = 2.dp
                            )
                        }
                    }
                }
            }
        }
    }

}
