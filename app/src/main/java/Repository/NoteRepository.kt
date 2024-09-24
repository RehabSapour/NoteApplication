package Repository

import Data.Note
import Data.NoteDao
import androidx.lifecycle.LiveData

class NoteRepository (private val Notedao : NoteDao) {

    val getAllNotes :LiveData<List<Note>> = Notedao.readNotes()

    suspend fun insertNotes(note:Note){
        Notedao.addNote(note)
    }

    suspend fun UpdateNotes(note:Note){
        Notedao.updateNote(note)
    }
    suspend fun deleteSingleNotes(note:Note){
        Notedao.deleteSingleNote(note)
    }
    suspend fun deleteAllNotes(){
        Notedao.deleteAllNotes()
    }
}