package ajithramesh.cr.movielistapp.adapter

import ajithramesh.cr.movielistapp.R
import ajithramesh.cr.movielistapp.models.MainModel
import ajithramesh.cr.movielistapp.ui.DetailsFragment
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
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

        // parsing data from array
        var titleValue = array.get(position).title
        var directorValue = array.get(position).director
        var producerValue = array.get(position).producer
        var releasevalue = array.get(position).release_date
        var descValue = array.get(position).opening_crawl


        var title = titleValue + convertDateToYear(releasevalue)
        holder.movieName.setText(title)
        holder.movieDes.setText(descValue)

        /*
         *
         * Recycler item click will open details page
         * data is also passing through the fragment transaction
         *
         */
        holder.rootCard.setOnClickListener {
            val detailsFragment =
                DetailsFragment(titleValue, directorValue, producerValue, releasevalue, descValue)

            val manager: FragmentManager =
                (context as AppCompatActivity).supportFragmentManager
            manager.beginTransaction()
                .add(R.id.main_container, detailsFragment, "detailsFragment")
                .addToBackStack("stack")
                .commit()
        }
    }


    /*
     *
     *
     *
     * Film releaseing date is inthe structure of 'YYYY-MM-DD'
     * date string is converted into date format
     * The Year is sort out from the date
     * (Year) is appended with the movie name
     *
     *
     *
     */

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
        val rootCard: CardView = itemView.findViewById(R.id.root_card);

    }
}