package Notefragments.addFragment

import Data.Note
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase2.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import viewModel.NoteViewModel


class addFragment : BottomSheetDialogFragment() {
     private lateinit var noteViewModel:NoteViewModel
     private lateinit var add_button : Button
     private lateinit var title: TextInputEditText
     private lateinit var dicription: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view=inflater.inflate(R.layout.fragment_add2, container, false)

       noteViewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        add_button = view.findViewById(R.id.add_btn)
        title= view.findViewById(R.id.title_editText)
        dicription =view.findViewById(R.id.discriptionEdittext)
        add_button.setOnClickListener{
            addNoteToDataBase()
        }

        return view
    }

    private fun addNoteToDataBase() {
          val title2 = title.text.toString()
        val dec2 = dicription.text.toString()
        if(check(title2 ,dec2)){
            val note = Note(0,title2,dec2)
            noteViewModel.addNote(note)
            Toast.makeText(requireActivity() , "Note Added Successfully", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireActivity() , "Failed To add", Toast.LENGTH_SHORT).show()
        }
    }
    private fun check(tilte:String , description :String):Boolean{
        return !(TextUtils.isEmpty(tilte) && TextUtils.isEmpty(description))

    }

}