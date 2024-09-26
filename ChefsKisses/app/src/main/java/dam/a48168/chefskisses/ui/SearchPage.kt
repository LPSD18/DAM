package dam.a48168.chefskisses.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import dam.a48168.chefskisses.R
import dam.a48168.chefskisses.adapters.OnTypeClickListener
import dam.a48168.chefskisses.adapters.TypeAdapter
import dam.a48168.chefskisses.data.IngredientType
import dam.a48168.chefskisses.model.MockData

class SearchPage: BottomNav(), OnTypeClickListener {
    override val contentViewId: Int
        get() = R.layout.search_page
    override val navigationMenuItemId: Int
        get() = R.id.search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var listView = findViewById<RecyclerView>(R.id.regionsRecyclerView)
        listView.adapter = TypeAdapter(typeList = MockData.type, context = this,this)
    }

    override fun onTypelick(type: IngredientType) {
        val intent = Intent(this, Meals::class.java)
        intent.putExtra("type",type.name)
        println("asdgbg" + type.name)
        startActivity(intent)
    }
}