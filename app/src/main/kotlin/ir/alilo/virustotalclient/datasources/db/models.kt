package ir.alilo.virustotalclient.datasources.db

import android.graphics.drawable.Drawable
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.ForeignKey
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import java.util.*

@Table(database = VirusTotalDatabase::class)
class App(@PrimaryKey @Column var packageName: String = "",
          @Column var name: String? = null,
          var icon: Drawable? = null,
          @Column(getterName = "getSystemApp") var systemApp: Boolean = false,
          @Column var danger: Float = 0f,
          @Column var result: String? = null,
          @Column var lastScan: Calendar? = null) : BaseModel()

@Table(database = VirusTotalDatabase::class)
class Scan(@PrimaryKey(autoincrement = true) @Column var id: Long = 0,
           @Column var name: String = "",
           @Column var result: String? = null,
           @Column @ForeignKey var app: App = App()) : BaseModel()
