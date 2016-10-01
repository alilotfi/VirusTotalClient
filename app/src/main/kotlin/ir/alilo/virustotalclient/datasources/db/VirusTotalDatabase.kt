package ir.alilo.virustotalclient.datasources.db

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = VirusTotalDatabase.NAME, version = VirusTotalDatabase.VERSION)
object VirusTotalDatabase {
    const val NAME = "VirusTotalDatabase"
    const val VERSION = 1
}