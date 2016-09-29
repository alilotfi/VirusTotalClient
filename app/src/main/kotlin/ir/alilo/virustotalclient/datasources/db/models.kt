package ir.alilo.virustotalclient.datasources.db

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.ForeignKey
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import java.util.*

@Table(database = VirusTotalDatabase::class)
class App() : BaseModel() {
    @PrimaryKey @Column var packageName: String = ""

    @Column var name: String = ""

    @Column var danger: Float = 0f

    @Column var result: String? = null

    @Column var lastScan: Calendar? = null

    @Column var systemApp: Int = 0
}

@Table(database = VirusTotalDatabase::class)
class Scan() : BaseModel() {
    @PrimaryKey(autoincrement = true) @Column var id: Long = 0

    @Column var name: String = ""

    @Column var result: String? = null

    @Column @ForeignKey var app: App = App()
}
