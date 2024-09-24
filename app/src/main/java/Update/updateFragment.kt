package Update

import Data.Note
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabase2.R
import com.google.android.material.textfield.TextInputEditText
import viewModel.NoteViewModel


class updateFragment : Fragment() {
    private lateinit var update_button : Button
    private lateinit var title: TextInputEditText
    private lateinit var dicription: TextInputEditText
    private lateinit var noteviewmodel : NoteViewModel
    private val args by navArgs<updateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view=  inflater.inflate(R.layout.fragment_update, container, false)
        noteviewmodel = ViewModelProvider(this).get(NoteViewModel::class.java)

        update_button = view.findViewById(R.id.update_btn)
        title= view.findViewById(R.id.updatetitle_editText)
        dicription =view.findViewById(R.id.updatediscriptionEdittext)
        // retrieve data
         title.setText(args.customObject.title)
         dicription.setText(args.customObject.discription)


        update_button.setOnClickListener{
            UpdateToDataBase()
        }

        //add menu
        setHasOptionsMenu(true)

        return view
    }


    private fun UpdateToDataBase() {

        val title2 = title.text.toString()
        val dec2 = dicription.text.toString()
        if(check(title2 ,dec2)){
           val note = Note(args.customObject.ID , title2, dec2)
            noteviewmodel.updateNote(note)
            Toast.makeText(requireActivity() , "Note updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireActivity() , "Failed To update", Toast.LENGTH_SHORT).show()
        }
    }
    private fun check(tilte:String , description :String):Boolean{
        return !(TextUtils.isEmpty(tilte) && TextUtils.isEmpty(description))

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.deletemenu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.delete){
            addNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addNotes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("YES"){ _,_ ->
           noteviewmodel.deleteSingleNote(args.customObject)
            Toast.makeText(requireActivity() , "Note Deleted Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("NO"){ _,_ ->

        }
        builder.create()
        builder.setMessage("Are you sure You Would delete This Note?")
        builder.show()
    }
}