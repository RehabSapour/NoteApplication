package adapter

import Data.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase2.R

class Listadapter(private val noteList : List<Note>,private val noteListner :noteClickListner) : RecyclerView.Adapter<Listadapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview =
            LayoutInflater.from(parent.context).inflate(R.layout.rowmodel, parent, false)
        return MyViewHolder(itemview)
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = noteList[position]
       holder.Title.text = current.title
       holder.desc.text = current.discription
        holder.itemView.setOnClickListener{
            noteListner.clickListner(current)
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Title = itemView.findViewById<TextView>(R.id.Title_id)
        val desc = itemView.findViewById<TextView>(R.id.Description_id)

    }
}

