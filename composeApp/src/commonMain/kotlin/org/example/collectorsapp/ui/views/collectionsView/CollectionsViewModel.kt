package org.example.collectorsapp.ui.views.collectionsView

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.collectorsapp.model.Collection
import org.example.collectorsapp.model.CollectionCategory

private val collectionList  = listOf(
    Collection(
        id = "1",
        name = "Kolekcja pokemon",
        description = "Kolekcja kart pokemon. Zawiera karty z różnych edycji.Karty są w różnych stanach, " +
                "od mint do damaged. Niektóre karty są bardzo rzadkie i mają dużą wartość.",
        imageBitmap = null,
        category = CollectionCategory.Cards,
        items = emptyList()
    ),
    Collection(
        id = "2",
        name = "Kolekcja zegarków",
        description = "Kolekcja zegarków. Zawiera zegarki z różnych edycji. Zegarki są w różnych stanach" +
                "od mint do damaged. Niektóre zegarki są bardzo rzadkie i mają dużą wartość.",
        imageBitmap = null,
        category = CollectionCategory.Watches,
        items = emptyList()
    ),
    Collection(
        id = "3",
        name = "Kolekcja monet",
        description = "Kolekcja monet. Zawiera monety z różnych edycji. Monety są w różnych stanach" +
                "od mint do damaged. Niektóre monety są bardzo rzadkie i mają dużą wartość.",
        imageBitmap = null,
        category = CollectionCategory.Coins,
        items = emptyList()
    ),
    Collection(
        id = "4",
        name = "Kolekcja instrumentów",
        description = "Kolekcja instrumentów",
        imageBitmap = null,
        category = CollectionCategory.Instruments,
        items = emptyList()
    ),
    Collection(
        id = "5",
        name = "Kolekcja książek",
        description = "Kolekcja książek",
        imageBitmap = null,
        category = CollectionCategory.Books,
        items = emptyList()
    )
)

class CollectionsViewModel: ViewModel() {
    private var data = collectionList

    private var _collectionsList = MutableStateFlow(data)
    val collectionsList = _collectionsList.asStateFlow()

    fun searchCollections(query: String): Unit {
        _collectionsList.update {
            data.filter { collection ->
                collection.name.contains(query, ignoreCase = true) ||
                        collection.description?.contains(query, ignoreCase = true) == true ||
                        collection.category.name.contains(query, ignoreCase = true)
            }
        }
    }
}