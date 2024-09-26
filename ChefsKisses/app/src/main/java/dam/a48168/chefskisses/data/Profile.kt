package dam.a48168.chefskisses.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


data class Profile(
    var username: String? = null,
    var email: String? = null,
    var password: String? = null
)