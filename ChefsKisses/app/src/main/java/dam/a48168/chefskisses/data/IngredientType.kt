package dam.a48168.chefskisses.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


data class IngredientType(
    val id: Int,
    val name: String,
    @DrawableRes val bg: Int
)