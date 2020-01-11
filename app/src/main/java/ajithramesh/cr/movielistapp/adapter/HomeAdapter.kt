package ajithramesh.cr.movielistapp.adapter

import ajithramesh.cr.movielistapp.R
import ajithramesh.cr.movielistapp.models.MainModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*


internal class HomeAdapter(val context: Context, val array: ArrayList<MainModel>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home, parent, false))
    }


    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var title = array.get(position).title + convertDateToYear(array.get(position).release_date)
        holder.movieName.setText(title)
        holder.movieDes.setText(array.get(position).opening_crawl)
        holder.rootCard.setOnClickListener {

        }
    }

    fun convertDateToYear(date: String): String? {
        try {
            val date1 = SimpleDateFormat("YYYY-MM-DD", Locale.getDefault()).parse(date)
            val calendar = Calendar.getInstance()
            calendar.setTime(date1!!)
            return " (" + calendar.get(Calendar.YEAR).toString() + ")"

        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieName: TextView = itemView.findViewById(R.id.movie_name);
        val movieDes: TextView = itemView.findViewById(R.id.movie_desc);
        val rootCard: TextView = itemView.findViewById(R.id.root_card);

    }
}