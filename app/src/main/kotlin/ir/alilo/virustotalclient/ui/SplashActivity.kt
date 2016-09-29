package ir.alilo.virustotalclient.ui

import android.app.Activity
import android.os.Bundle
import ir.alilo.virustotalclient.R

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}