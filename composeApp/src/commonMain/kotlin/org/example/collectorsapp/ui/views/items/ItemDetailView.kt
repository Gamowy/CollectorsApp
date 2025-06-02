package org.example.collectorsapp.ui.views.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.preat.peekaboo.image.picker.toImageBitmap
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.item_description_label_input
import kotlinproject.composeapp.generated.resources.item_dropdown_label
import kotlinproject.composeapp.generated.resources.item_image_description
import kotlinproject.composeapp.generated.resources.item_name_label_input
import kotlinproject.composeapp.generated.resources.item_value_label_input
import kotlinproject.composeapp.generated.resources.not_available_description
import kotlinproject.composeapp.generated.resources.placeholder
import org.example.collectorsapp.viewmodels.ItemDetailsViewmodel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ItemDetailView(
    viewModel: ItemDetailsViewmodel,
    modifier: Modifier = Modifier
) {
    val item by viewModel.item.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top=10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                if (item.imageBitmap != null) {
                    Image(
                        bitmap = item.imageBitmap!!.toImageBitmap(),
                        contentDescription = stringResource(Res.string.item_image_description),
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(LocalWindowInfo.current.containerSize.height.dp*0.15f)
                            .clip(RoundedCornerShape(8.dp))
                    )
                } else {
                    Image(
                        painter = painterResource(Res.drawable.placeholder),
                        contentDescription = stringResource(Res.string.item_image_description),
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxWidth()
                            .width(LocalWindowInfo.current.containerSize.height.dp*0.15f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.hsv(52f, 0.13f, 0.91f))
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top=10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = stringResource(Res.string.item_name_label_input),
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                    Text(text = item.name,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                    Text(text = stringResource(Res.string.item_description_label_input),
                        fontSize = 20.sp,
                        maxLines = 6,
                        overflow = TextOverflow.Ellipsis)
                    Text(text = item.description ?: stringResource(Res.string.not_available_description),
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                    Text(text = stringResource(Res.string.item_value_label_input),
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                    Text(text = item.estimatedValue?.let {
                        if (it > 0) "\$$it"
                        else "\$0"
                    } ?: "\$0",
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                    Text(text = stringResource(Res.string.item_dropdown_label),
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                    Text(text = item.condition.name,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}