package dam.a48168.chefskisses.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dam.a48168.chefskisses.R

class Login: AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var button: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login)
        editTextUsername = findViewById(R.id.userNamefield)
        editTextPassword = findViewById(R.id.passwordField)
        button = findViewById(R.id.loginButton)
        auth = FirebaseAuth.getInstance()

        val goToRegisterTextView: TextView = findViewById(R.id.goToRegister)
        goToRegisterTextView.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val goToHomeTextView: TextView = findViewById(R.id.goToHome)
        goToHomeTextView.setOnClickListener{
            val intent = Intent(this,HomePage::class.java)
            startActivity(intent)
        }

        button.setOnClickListener(View.OnClickListener {
            var email = editTextUsername.text.toString()
            var password = editTextPassword.text.toString()
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        val user = auth.currentUser
                        val intent = Intent(this,ProfilePage::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(baseContext,"Authentication failed",Toast.LENGTH_SHORT)
                    }
                }
        })


    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser!=null){
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }


    }

}