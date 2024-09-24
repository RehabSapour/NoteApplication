package Data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val ID:Int,
    val title : String,
    val discription:String
):Parcelable
