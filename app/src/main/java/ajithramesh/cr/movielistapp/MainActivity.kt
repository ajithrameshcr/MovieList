package ajithramesh.cr.movielistapp

import ajithramesh.cr.movielistapp.ui.SplashScreen
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onStart() {
        super.onStart()
//        if (savedInstanceState == null) {
            val splashScreen = SplashScreen()
            supportFragmentManager.beginTransaction().add(R.id.main_container, splashScreen)
                .commit()
//        }
    }
}
