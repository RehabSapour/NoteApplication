package Notefragments.addFragment.listfragment

import Data.Note
import Notefragments.addFragment.addFragment
import adapter.Listadapter
import adapter.noteClickListner
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import viewModel.NoteViewModel


class ListFragment : Fragment() , noteClickListner{
  private lateinit var addbuttom : FloatingActionButton
  lateinit var noteViewModel: NoteViewModel
  lateinit var noteRecyclerView: RecyclerView
  lateinit var noteList : List<Note>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list2, container, false)
        //Recyclerview
        noteRecyclerView = view.findViewById(R.id.recyclerView)
        noteList = ArrayList<Note>()

        // NoteViewModel
        noteViewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)
        noteViewModel.readallnotes.observe(viewLifecycleOwner, Observer { note ->
            noteRecyclerView.adapter = Listadapter(note, this)
        })

        //FloatingActionButton
        addbuttom = view.findViewById(R.id.floatingActionButton)

        addbuttom.setOnClickListener {
            // findNavController().navigate(R.id.action_listFragment_to_addFragment)
            addFragment().show(parentFragmentManager, "NewTask")
        }
        //add menu
        setHasOptionsMenu(true)
        return view
    }

    override fun clickListner(note: Note) {
        Toast.makeText(requireActivity(), "Clicked ${note.ID}", Toast.LENGTH_SHORT).show()
        val action = ListFragmentDirections.actionListFragmentToUpdateFragment(note)
        findNavController().navigate(action)
    }



    //For deleting Note
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.deletemenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.delete) {
            addNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addNotes() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("YES") { _, _ ->
            noteViewModel.deleteAllNote()
            Toast.makeText(requireActivity(), "Notes Deleted Successfully", Toast.LENGTH_SHORT)
                .show()
        }
        builder.setNegativeButton("NO") { _, _ ->

        }
        builder.create()
        builder.setMessage("Are you sure You Would delete All Notes ?")
        builder.show()
    }

}