package com.example.brawlstarsinfohub

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Brawler(
    var name: String? = null,
    var rarity: String? = null,
    var health: Int = 0,
    var damage: Int = 0,
    var imageId:Int
) : Parcelable
