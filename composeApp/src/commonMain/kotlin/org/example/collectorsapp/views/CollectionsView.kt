package org.example.collectorsapp.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.search
import org.example.collectorsapp.components.CollectionCard
import org.example.collectorsapp.components.NewCollectionButton
import org.example.collectorsapp.components.SearchBar
import org.example.collectorsapp.model.Collection
import org.example.collectorsapp.model.CollectionCategory
import org.jetbrains.compose.resources.stringResource
import kotlinproject.composeapp.generated.resources.button_new_collection
import kotlinproject.composeapp.generated.resources.plus

val collectionList  = listOf(
    Collection(
        id = "1",
        name = "Kolekcja pokemon",
        description = "Kolekcja kart pokemon. Zawiera karty z różnych edycji.Karty są w różnych stanach, " +
                "od mint do damaged. Niektóre karty są bardzo rzadkie i mają dużą wartość.",
        imageBitmap = null,
        category = CollectionCategory.CARDS,
        items = emptyList()
    ),
    Collection(
        id = "2",
        name = "Kolekcja zegarków",
        description = "Kolekcja zegarków. Zawiera zegarki z różnych edycji. Zegarki są w różnych stanach" +
                "od mint do damaged. Niektóre zegarki są bardzo rzadkie i mają dużą wartość.",
        imageBitmap = null,
        category = CollectionCategory.WATCHES,
        items = emptyList()
    ),
    Collection(
        id = "3",
        name = "Kolekcja monet",
        description = "Kolekcja monet. Zawiera monety z różnych edycji. Monety są w różnych stanach" +
                "od mint do damaged. Niektóre monety są bardzo rzadkie i mają dużą wartość.",
        imageBitmap = null,
        category = CollectionCategory.COINS,
        items = emptyList()
    ),
    Collection(
        id = "4",
        name = "Kolekcja instrumentów",
        description = "Kolekcja instrumentów",
        imageBitmap = null,
        category = CollectionCategory.INSTRUMENTS,
        items = emptyList()
    ),
    Collection(
        id = "5",
        name = "Kolekcja książek",
        description = "Kolekcja książek",
        imageBitmap = null,
        category = CollectionCategory.BOOKS,
        items = emptyList()
    )
)

@Composable
fun CollectionsView(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var collectionList by remember { mutableStateOf(collectionList) }
    Box (modifier = modifier
        .fillMaxSize()
        .padding(12.dp, 0.dp, 12.dp, 0.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            SearchBar("", onValueChange = {
                collectionList = searchCollections(it)
            })
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(collectionList.size) { index ->
                    CollectionCard(collection = collectionList[index])
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
        NewCollectionButton(
            stringResource(Res.string.button_new_collection),
            Res.drawable.plus,
            onClick = {
                navController.navigate("add_collection")
            },
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

private fun searchCollections(query: String): List<Collection> {
    return collectionList.filter { collection ->
        collection.name.contains(query, ignoreCase = true) ||
                collection.description?.contains(query, ignoreCase = true) == true ||
                collection.category.name.contains(query, ignoreCase = true)
    }
}
