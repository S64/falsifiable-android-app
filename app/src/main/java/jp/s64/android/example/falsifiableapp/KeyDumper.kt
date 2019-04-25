package jp.s64.android.example.falsifiableapp

import android.content.Context
import android.widget.TextView
import java.security.cert.CertificateFactory

object KeyDumper {

    private val cfFac = CertificateFactory.getInstance("X509")

    private fun dump(context: Context): String {
        return cfFac.generateCertificate(
            context.resources.openRawResource(
                R.raw.myca
            )
        ).toString()
    }

    fun dump(textView: TextView) {
        textView.text = dump(textView.context)
    }

}
