package Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note : Note)
  // using suspend meaning stop function or run it at the time I want

    @Update
    suspend fun updateNote(note : Note)

    @Delete
    suspend fun deleteSingleNote(note : Note)

    @Query("delete from note_table")
    suspend fun deleteAllNotes()

    @Query("select * from note_table order by id asc")
    fun readNotes(): LiveData<List<Note>>

}