package com.jvrni.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.jvrni.core.designsystem.components.UserCard
import com.jvrni.core.designsystem.components.UserCardEntity
import com.jvrni.core.designsystem.theme.Colors
import com.jvrni.core.designsystem.theme.Dimens
import androidx.paging.compose.items
import com.jvrni.core.designsystem.components.Loading
import com.jvrni.core.domain.models.User

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onAction: (User) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val users = state.data.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background)
            .navigationBarsPadding()
            .statusBarsPadding(),
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
                    onAction.invoke(user)
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