package ir.alilo.virustotalclient.features.settings.about

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ir.alilo.virustotalclient.R
import ir.alilo.virustotalclient.ui.click
import kotlinx.android.synthetic.main.activity_about.*
import org.jetbrains.anko.browse
import java.util.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar)

        val licenseIS = resources.openRawResource(R.raw.opensource)
        val s = Scanner(licenseIS).useDelimiter("\\A")
        val licenseContent = if (s.hasNext()) s.next() else ""
        license.text = licenseContent

        website.click { browse(WEBSITE) }
        source.click { browse(SOURCE) }
    }

    companion object {
        val WEBSITE = "http://alilo.ir"
        val SOURCE = "https://github.com/alilotfi/VirusTotalClient"
    }
}