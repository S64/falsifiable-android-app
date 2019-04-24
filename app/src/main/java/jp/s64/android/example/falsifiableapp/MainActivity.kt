package jp.s64.android.example.falsifiableapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*

class MainActivity : AppCompatActivity() {

    companion object {

        private const val ENDPOINT = "https://example.com"

    }

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button)
            .setOnClickListener {
                val req = Request.Builder()
                    .url(ENDPOINT)
                    .build()

                GlobalScope.launch(Dispatchers.IO) {
                    val result = client.newCall(req).execute().let {
                        it.body()?.string() ?: it.code().toString()
                    }

                    GlobalScope.launch(Dispatchers.Main) {
                        findViewById<TextView>(R.id.text)
                            .text = result
                    }
                }
            }
    }
}
