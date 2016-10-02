package ir.alilo.virustotalclient.features.tos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ir.alilo.virustotalclient.R
import ir.alilo.virustotalclient.datasources.Preference
import ir.alilo.virustotalclient.features.applist.AppListActivity
import ir.alilo.virustotalclient.ui.click
import kotlinx.android.synthetic.main.activity_tos.*

class ToSActivity : AppCompatActivity() {
    var shown by Preference("ToSActivity:shown:Boolean", false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (shown) {
            goToMain()
            return
        }

        setContentView(R.layout.activity_tos)

        confirmation.click {
            val tosIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.tos_tosAddress)))
            startActivity(tosIntent)
        }

        accept.click {
            shown = true
            goToMain()
        }
    }

    private fun goToMain() {
        val mainIntent = Intent(this, AppListActivity::class.java)
        startActivity(mainIntent)
        finish()
    }
}