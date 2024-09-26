package dam.a48168.chefskisses.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import dam.a48168.chefskisses.R
import dam.a48168.chefskisses.viewmodel.MealsViewModel
import dam.a48168.chefskisses.adapters.OnMealClickListener
import dam.a48168.chefskisses.adapters.profileAdapter
import dam.a48168.chefskisses.data.MealDetail
import dam.a48168.chefskisses.databinding.ProfilePageBinding


class ProfilePage : BottomNav(), OnMealClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseData: DatabaseReference
    private val viewModel: MealsViewModel by viewModels()

    override val contentViewId: Int
        get() = R.layout.profile_page
    override val navigationMenuItemId: Int
        get() = R.id.profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Correctly initialize binding
        val mealsBinding = binding as ProfilePageBinding
        val listView = mealsBinding.mymealsRecyclerView

        auth = FirebaseAuth.getInstance()
        firebaseData = FirebaseDatabase.getInstance().reference

        if (auth.currentUser == null) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
            return
        }

        val user = auth.currentUser
        val email = user?.email
        val userNameTextView = mealsBinding.userName

        if (email != null) {
            fetchUsername(email, userNameTextView)
            viewModel.fetchMyMeals(email)
        }

        mealsBinding.signOutButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        mealsBinding.profileImage.setOnClickListener{
            val intent = Intent(this,AboutMe::class.java)
            startActivity(intent)
        }

        viewModel.meals.observe(this) {
            listView.adapter = it?.let { meals ->
                profileAdapter(meals, this,this)
            }
        }
    }

    private fun fetchUsername(email: String, textView: TextView) {
        firebaseData.child("users").orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (userSnapshot in dataSnapshot.children) {
                        val username = userSnapshot.child("username").value.toString()
                        textView.text = username
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("ProfilePage", "loadUsername:onCancelled", databaseError.toException())
                }
            })
    }

    override fun onMealClick(meal: MealDetail) {
        val intent = Intent(this,MealPage::class.java)
        intent.putExtra("mealID",meal.mealID)
        startActivity(intent)
    }
}