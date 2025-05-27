package org.example.collectorsapp.ui.components.topbars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.app_name
import kotlinproject.composeapp.generated.resources.arrow_left
import kotlinproject.composeapp.generated.resources.delete
import kotlinproject.composeapp.generated.resources.delete_collection
import kotlinproject.composeapp.generated.resources.delete_collection_confirmation
import kotlinproject.composeapp.generated.resources.edit_collection
import kotlinproject.composeapp.generated.resources.pencil
import kotlinproject.composeapp.generated.resources.top_bar_back_button_description
import org.example.collectorsapp.ui.components.PopupDialog
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    navHost: NavHostController,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {

    var showAlertDialog by remember { mutableStateOf(false) }

    if (showAlertDialog) {
        PopupDialog(popUpText = stringResource(Res.string.delete_collection_confirmation),
            onDismiss = {
                showAlertDialog = false
            },
            onConfirm = {
                showAlertDialog = false
                onDelete()
                navHost.popBackStack()
            })
    }
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(Res.string.app_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navHost.popBackStack()
                },
                content = {
                    Icon(
                        painter = painterResource(Res.drawable.arrow_left),
                        contentDescription = stringResource(Res.string.top_bar_back_button_description),
                        modifier = Modifier.size(35.dp).padding(4.dp)
                    )
                }
            )
        },
        actions = {
            Row {
                Icon(
                    painter = painterResource(Res.drawable.pencil),
                    contentDescription = stringResource(Res.string.edit_collection),
                    modifier = Modifier
                        .size(35.dp)
                        .padding(4.dp)
                        .clickable {
                            onEdit()
                        }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    painter = painterResource(Res.drawable.delete),
                    contentDescription = stringResource(Res.string.delete_collection),
                    modifier = Modifier
                        .size(35.dp)
                        .padding(4.dp)
                        .clickable {
                            showAlertDialog = true
                        }
                )
            }
        },
        scrollBehavior = pinnedScrollBehavior(),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(NavigationBarDefaults.Elevation, shape = MaterialTheme.shapes.large)
    )
}