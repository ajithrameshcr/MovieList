package ajithramesh.cr.movielistapp.ui


import ajithramesh.cr.movielistapp.R
import ajithramesh.cr.movielistapp.adapter.HomeAdapter
import ajithramesh.cr.movielistapp.models.MainModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.view.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import javax.security.auth.callback.Callback

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var mClient: OkHttpClient
    private var MainArray: ArrayList<MainModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        v.homeRecycler.setHasFixedSize(true)
        v.homeRecycler.layoutManager = LinearLayoutManager(context)


        return v
    }

    /*
     * API is called here
     */
    private fun fetchData() {
        mClient = OkHttpClient();
        val request = Request.Builder()
            .url("https://swapi.co/api/films/")
            .addHeader("Content-Type", " application/x-www-form-urlencoded")
            .build()

        mClient.newCall(request).enqueue(object : Callback, okhttp3.Callback {

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                activity!!.runOnUiThread {

                    Log.d("loga", "err: " + e)
                    Toast.makeText(
                        context,
                        "error: " + e,
                        Toast.LENGTH_LONG
                    ).show()
                }

                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                if (response.isSuccessful) {
                    val responseString = response.body!!.string()
                    Log.d("loga", "succ: " + responseString)

                    activity!!.runOnUiThread {

                        try {
                            val responseJson = JSONObject(responseString)
                            val results: JSONArray = responseJson.getJSONArray("results")
                            MainArray.clear()
                            for (x in 0..results.length() - 1) {
                                val currentObject = results.getJSONObject(x)
                                val titleValue = currentObject.getString("title")
                                val episode_id = currentObject.getInt("episode_id").toString()
                                val opening_crawl = currentObject.getString("opening_crawl")
                                val director = currentObject.getString("director")
                                val producer = currentObject.getString("producer")
                                val release_date = currentObject.getString("release_date")
                                Log.d("loga", "succ: " + titleValue)

                                MainArray.add(
                                    MainModel(
                                        titleValue,
                                        episode_id,
                                        opening_crawl,
                                        director,
                                        producer,
                                        release_date
                                    )
                                )
                            }

                            view!!.homeRecycler.adapter = HomeAdapter(context!!, MainArray!!)
                            view!!.home_progress.visibility = View.INVISIBLE

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                }
            }

        })

    }
}
