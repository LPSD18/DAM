package dam.a48168.aboutphone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Build
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val multiLine = findViewById<TextView>(R.id.TextView);
        val buildInfo = """
            Manufacturer: ${Build.MANUFACTURER}
            Model: ${Build.MODEL}
            Brand: ${Build.BRAND}
            Type: ${Build.TYPE}
            User: ${Build.USER}
            Base: ${Build.VERSION.BASE_OS}
            Incremental: ${Build.VERSION.INCREMENTAL}
            SDK Version: ${Build.VERSION.SDK_INT}
            Version Code: ${Build.VERSION.CODENAME}
            Display: ${Build.DISPLAY}
        """.trimIndent()

        multiLine.text = buildInfo;
    }
}