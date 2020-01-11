package ajithramesh.cr.movielistapp.ui


import ajithramesh.cr.movielistapp.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_details.view.*

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment(
    val titleValue: String,
    val directorValue: String,
    val producerValue: String,
    val releaseDateValue: String,
    val openingCrawlValue: String
) : Fragment() {

    companion object {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_details, container, false)

        // Setting data
        v.title.setText(titleValue)
        v.director.setText(directorValue)
        v.producer.setText(producerValue)
        v.release.setText(releaseDateValue)
        v.des.setText(openingCrawlValue)


        // handling backButton in actionbar
        v.details_back.setOnClickListener {
            if (getFragmentManager()!!.backStackEntryCount > 0) {
                getFragmentManager()!!.popBackStack();
            }
        }
        return v
    }


}
