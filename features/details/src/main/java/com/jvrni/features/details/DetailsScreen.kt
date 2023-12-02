package com.jvrni.features.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jvrni.core.designsystem.theme.Colors
import com.jvrni.core.designsystem.theme.Dimens
import com.jvrni.core.designsystem.theme.Radius
import com.jvrni.core.domain.convertDate
import com.jvrni.core.domain.formatDate
import com.jvrni.core.domain.models.User

@Composable
fun DetailsScreen(
    user: User,
    onBack: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.background)
    ) {
        val (back, image, column) = createRefs()

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.picture)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxHeight(0.4f)
                .clip(
                    RoundedCornerShape(
                        bottomStart = Radius.extraLarge * 2,
                        bottomEnd = Radius.extraLarge * 2
                    )
                )
        )

        Card(
            modifier = Modifier
                .constrainAs(back) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .statusBarsPadding()
                .padding(vertical = Dimens.space8, horizontal = Dimens.space16)
                .clip(RoundedCornerShape(Dimens.space16))
                .clickable { onBack.invoke() },
            shape = RoundedCornerShape(Dimens.space16),
            colors = CardDefaults.cardColors(containerColor = Colors.background)
        ) {
            Icon(
                modifier = Modifier
                    .size(45.dp)
                    .padding(Dimens.space4),
                painter = painterResource(id = com.jvrni.core.designsystem.R.drawable.ic_arrow_left),
                tint = Colors.primary,
                contentDescription = ""
            )
        }

        Column(
            modifier = Modifier
                .constrainAs(column) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                }
                .padding(horizontal = Dimens.space16)
                .padding(top = Dimens.space32),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "${user.name} ${user.lastName}",
                style = TextStyle(
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp
                )
            )

            Text(
                text = user.email,
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            )

            Text(
                text = user.phone,
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            )

            Text(
                modifier = Modifier.padding(top = Dimens.space24),
                text = stringResource(id = R.string.location_label),
                style = TextStyle(
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp
                )
            )

            Text(
                text = "${user.location.number} ${user.location.state}\n" +
                        "${user.location.city}, ${user.location.state}, ${user.location.country}",
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            )

            Text(
                modifier = Modifier.padding(top = Dimens.space24),
                text = stringResource(id = R.string.creted_by_label),
                style = TextStyle(
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp
                )
            )

            Text(
                text = formatDate(convertDate(user.registeredDate)),
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            )
        }
    }
}
