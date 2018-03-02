package ir.alilo.virustotalclient.utils

import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest

object SHACheckSumExample {
    fun calculate(file: File) {
        val md = MessageDigest.getInstance("SHA-256")
        val fis = FileInputStream(file)
        val dataBytes = ByteArray(1024)

        var nread = 0
        while ((nread = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread)
        }
        val mdbytes = md.digest()

        //convert the byte to hex format method 1
        val sb = StringBuffer()
        for (i in mdbytes.indices) {
            sb.append(Integer.toString((mdbytes[i] and 0xff) + 0x100, 16).substring(1))
        }

        println("Hex format : " + sb.toString())

        //convert the byte to hex format method 2
        val hexString = StringBuffer()
        for (i in mdbytes.indices) {
            hexString.append(Integer.toHexString(0xFF and mdbytes[i]))
        }

        println("Hex format : " + hexString.toString())
    }
}