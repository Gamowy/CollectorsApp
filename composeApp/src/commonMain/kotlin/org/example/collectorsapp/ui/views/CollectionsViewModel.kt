package org.example.collectorsapp.ui.views

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import org.example.collectorsapp.model.CollectionCategory
import org.example.collectorsapp.model.ItemsCollection

private val collectionList  = listOf(
    ItemsCollection(
        collectionId = 1L,
        name = "Kolekcja pokemon",
        description = "Kolekcja kart pokemon. Zawiera karty z różnych edycji.Karty są w różnych stanach, " +
                "od mint do damaged. Niektóre karty są bardzo rzadkie i mają dużą wartość.",
        image = null,
        category = CollectionCategory.Cards,
    ),
    ItemsCollection(
        collectionId = 2L,
        name = "Kolekcja zegarków",
        description = "Kolekcja zegarków. Zawiera zegarki z różnych edycji. Zegarki są w różnych stanach" +
                "od mint do damaged. Niektóre zegarki są bardzo rzadkie i mają dużą wartość.",
        image = null,
        category = CollectionCategory.Watches,
    ),
    ItemsCollection(
        collectionId = 3L,
        name = "Kolekcja monet",
        description = "Kolekcja monet. Zawiera monety z różnych edycji. Monety są w różnych stanach" +
                "od mint do damaged. Niektóre monety są bardzo rzadkie i mają dużą wartość.",
        image = null,
        category = CollectionCategory.Coins,
    ),
    ItemsCollection(
        collectionId = 4L,
        name = "Kolekcja instrumentów",
        description = "Kolekcja instrumentów",
        image = null,
        category = CollectionCategory.Instruments,
    ),
    ItemsCollection(
        collectionId = 5L,
        name = "Kolekcja książek",
        description = "Kolekcja książek",
        image = null,
        category = CollectionCategory.Books,
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