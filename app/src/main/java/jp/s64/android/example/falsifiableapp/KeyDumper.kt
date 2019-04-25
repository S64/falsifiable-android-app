package jp.s64.android.example.falsifiableapp

import android.content.Context
import android.widget.TextView
import org.apache.commons.io.IOUtils
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec

object KeyDumper {

    private val factory = KeyFactory.getInstance("RSA")

    private fun dump(context: Context): String {
        val keySpec = X509EncodedKeySpec(
            IOUtils.toByteArray(context.resources.openRawResource(
                R.raw.myca
            ))
        )
        val pk = factory.generatePublic(keySpec)

        return pk.toString()
    }

    fun dump(textView: TextView) {
        textView.text = dump(textView.context)
    }

}
