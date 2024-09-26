package dam.a48168.chefskisses.firebase

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class FirebaseBeg : Application() {

    private lateinit var firebaseData : DatabaseReference

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)


    }
}
