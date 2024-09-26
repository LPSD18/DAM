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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dam.a48168.chefskisses.R
import dam.a48168.chefskisses.data.Profile

class Register : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var button: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseData : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.register)

        // Initialize views here
        editTextEmail = findViewById(R.id.emailfieldRegister)
        editTextPassword = findViewById(R.id.passwordFieldRegister)
        editTextUsername = findViewById(R.id.UserNameTextField)
        button = findViewById(R.id.registerButton)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        firebaseData = FirebaseDatabase.getInstance().getReference("users")

        fun addUser(username:String, email:String, password:String){
            val newUserRef = firebaseData.push()
            val user = Profile(username,email,password)
            newUserRef.setValue(user)
        }

        val goToLoginTextView: TextView = findViewById(R.id.goToLogin)
        goToLoginTextView.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        button.setOnClickListener(View.OnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val username = editTextUsername.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    addUser(username,email,password)
                    val intent = Intent(this, ProfilePage::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is already signed in
            val intent = Intent(this, ProfilePage::class.java)
            startActivity(intent)

        }
    }
}
