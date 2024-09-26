package dam.a48168.chefskisses.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import dam.a48168.chefskisses.R
import androidx.viewbinding.ViewBinding
import com.google.firebase.auth.FirebaseAuth

abstract class BottomNav : AppCompatActivity() {

    lateinit var navigationView: BottomNavigationView
    lateinit var binding: ViewBinding

    abstract val contentViewId: Int
    abstract val navigationMenuItemId: Int

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        auth = FirebaseAuth.getInstance()

        try {
            binding = DataBindingUtil.setContentView(this, contentViewId)
            println("Content view set successfully with ID: $contentViewId")
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Unable to set content view with the given layout resource ID: $contentViewId")
        }

        try {
            navigationView = findViewById(R.id.navigation)
            if (navigationView == null) {
                throw RuntimeException("Navigation view not found in layout")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Error initializing navigation view")
        }

        navigationView.itemIconTintList = null
        navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                    true
                }
                R.id.add -> {
                    if(auth.currentUser!=null) {
                        val intent = Intent(this, AddPage::class.java)
                        startActivity(intent)
                        true
                    }
                    else{
                        val intent = Intent(this, HomePage::class.java)
                        startActivity(intent)
                        true
                    }
                }
                R.id.search -> {
                    val intent = Intent(this, SearchPage::class.java)
                    startActivity(intent)
                    true
                }
                R.id.save ->{
                    if(auth.currentUser!=null) {
                        val intent = Intent(this, SavedPage::class.java)
                        startActivity(intent)
                        true
                    }
                    else{
                        val intent = Intent(this, HomePage::class.java)
                        startActivity(intent)
                        true
                    }
                }
                R.id.profile -> {
                    val intent = Intent(this, ProfilePage::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    override fun onStart() {
        super.onStart()
        updateNavigationBarState()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    private fun updateNavigationBarState() {
        val actionId = navigationMenuItemId
        selectBottomNavigationBarItem(actionId)
    }

    private fun selectBottomNavigationBarItem(itemId: Int) {
        val item = navigationView.menu.findItem(itemId)
        item.isChecked = true
    }
}
