package viewModel

import Data.Note
import Data.NoteDataBase
import Repository.NoteRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val readallnotes:LiveData<List<Note>>

    private val repo : NoteRepository

    init {
        val dataDao = NoteDataBase.getInstance(application).Notedao()
        repo = NoteRepository(dataDao)
        readallnotes = repo.getAllNotes
    }

    fun addNote(note:Note){
       // CoroutineDispatcher
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertNotes(note)
        }
    }

    fun updateNote(note:Note){
        // CoroutineDispatcher
        viewModelScope.launch(Dispatchers.IO) {
            repo.UpdateNotes(note)
        }
    }

    fun deleteSingleNote(note:Note){
        // CoroutineDispatcher
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteSingleNotes(note)
        }
    }

    fun deleteAllNote(){
        // CoroutineDispatcher
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAllNotes()
        }
    }


}