package dam.a48168.chefskisses.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dam.a48168.chefskisses.R

class AboutMe: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.about_me)
        val goBack : ImageView = findViewById(R.id.goBackAbout)
        goBack.setOnClickListener{
            onBackPressed()
        }
    }
}