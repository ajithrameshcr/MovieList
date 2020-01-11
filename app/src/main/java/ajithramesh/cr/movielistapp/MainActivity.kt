package ajithramesh.cr.movielistapp

import ajithramesh.cr.movielistapp.ui.SplashScreen
import ajithramesh.cr.movielistapp.utils.ConnectivityReceiver
import android.annotation.SuppressLint
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener  {
    private var mSnackBar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // registering receiver
        registerReceiver(ConnectivityReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

    }

    override fun onStart() {
        super.onStart()
            val splashScreen = SplashScreen()
            supportFragmentManager.beginTransaction().add(R.id.main_container, splashScreen)
                .commit()
    }


    // No network view
    @SuppressLint("WrongConstant")
    private fun showMessage(isConnected: Boolean) {

        if (!isConnected) {
            val messageToUser = "You are offline now."
            mSnackBar = Snackbar.make(findViewById(R.id.main_container), messageToUser, Snackbar.LENGTH_LONG) //Assume "rootLayout" as the root layout of every activity.
            mSnackBar?.duration = Snackbar.LENGTH_INDEFINITE
            mSnackBar?.show()
        } else {
            mSnackBar?.dismiss()
        }

    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    /**
     * Callback will be called when there is change
     */
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }
}
