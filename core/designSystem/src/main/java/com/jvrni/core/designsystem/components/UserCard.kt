package com.jvrni.core.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jvrni.core.designsystem.theme.Colors
import com.jvrni.core.designsystem.theme.Dimens
import com.jvrni.core.designsystem.theme.VlueChallengeTheme

@Composable
fun UserCard(
    entity: UserCardEntity,
    background: Color = Colors.primary,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clip(CircleShape).clickable { onClick.invoke() },
        colors = CardDefaults.cardColors(contentColor = background),
        shape = CircleShape
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(entity.picture)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier.padding(start = Dimens.space16),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = entity.name,
                    color = Color.Black
                )
                Text(
                    text = entity.email,
                    color = Color.Black
                )
            }
        }
    }
}

data class UserCardEntity(
    val name: String,
    val email: String,
    val picture: String
)

@Preview()
@Composable
private fun PrevUserCard() {
    VlueChallengeTheme {
        UserCard(
            entity = UserCardEntity(
                name = "João Varani",
                email = "joao.varani@vlue.com",
                picture = "https://images.unsplash.com/photo-1633332755192-727a05c4013d?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8dXNlcnxlbnwwfHwwfHx8MA%3D%3D"
            )
        ) {}
    }
}