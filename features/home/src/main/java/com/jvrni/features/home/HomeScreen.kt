package com.jvrni.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.jvrni.core.designsystem.components.UserCard
import com.jvrni.core.designsystem.components.UserCardEntity
import com.jvrni.core.designsystem.theme.Colors
import com.jvrni.core.designsystem.theme.Dimens
import androidx.paging.compose.items
import com.jvrni.core.designsystem.components.Loading

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val users = viewModel.state.collectAsState().value.data.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.space16),
        contentPadding = PaddingValues(Dimens.space16)
    ) {
        items(users) { user ->
            user?.let {
                UserCard(
                    entity = UserCardEntity(
                        name = "${user.name} ${user.lastName}",
                        email = user.email,
                        picture = user.picture
                    )
                ) {
                    viewModel.navigateToDetails(user)
                }
            }
        }

        when {
            users.loadState.append is LoadState.Loading -> item {
                Loading(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimens.space8)
                )
            }

            users.loadState.refresh is LoadState.Loading -> item {
                Loading(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .background(Colors.background)
                )
            }
        }
    }
}