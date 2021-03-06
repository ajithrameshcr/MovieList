package ajithramesh.cr.movielistapp.ui


import ajithramesh.cr.movielistapp.R
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class SplashScreen : Fragment() {

    private var handler: Handler? = null
    private var ru: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_splash_screen, container, false)


        // 1800 milli sec delay for splash page We can call API here for faster data parsing
        handler = Handler()
        ru = object : Runnable {
            override fun run() {
                val homeFrag: HomeFragment = HomeFragment()
                fragmentManager!!.beginTransaction()
                    .replace(R.id.main_container, homeFrag, "homeFrag").commit()

            }
        }




        return v
    }

    override fun onPause() {
        super.onPause()
        handler!!.removeCallbacks(ru)
    }

    override fun onResume() {
        super.onResume()
        handler!!.postDelayed(ru, 1800)
    }
}

