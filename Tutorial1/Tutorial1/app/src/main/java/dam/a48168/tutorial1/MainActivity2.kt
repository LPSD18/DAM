package dam.a48168.tutorial1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
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
        configureButton()
    }

    private fun configureButton(){
        val button : Button = findViewById(R.id.button2)
        button.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        })
    }
}