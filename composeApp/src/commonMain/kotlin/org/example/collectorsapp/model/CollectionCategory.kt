package org.example.collectorsapp.model

import kotlinx.serialization.Serializable

@Serializable
enum class CollectionCategory {
    Anything,
    Cards,
    Watches,
    Coins,
    Stamps,
    Toys,
    Art,
    Books,
    Games,
    Music,
    Movies,
    Clothes,
    Instruments,
    Other
}