package dam.a48168.tutorial1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureButton()
    }

    private fun configureButton(){
        val button : Button = findViewById(R.id.button)
        button.setOnClickListener(View.OnClickListener {
            val intent : Intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        })
    }
}