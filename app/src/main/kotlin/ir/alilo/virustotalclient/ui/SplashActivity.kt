package ir.alilo.virustotalclient.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import ir.alilo.virustotalclient.R
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    val SPLASH_DURATION = TimeUnit.SECONDS.toMillis(2)
    val handler = Handler()
    val startActivityRunnable = Runnable {
        val mainIntent = Intent(this, SplashActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(startActivityRunnable, SPLASH_DURATION)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks { startActivityRunnable }
    }
}