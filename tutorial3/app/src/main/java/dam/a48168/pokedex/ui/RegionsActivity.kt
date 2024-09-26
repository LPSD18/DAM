package dam.a48168.pokedex.ui

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import dam.a48168.pokedex.R
import dam.a48168.pokedex.data.PokemonRegion
import dam.a48168.pokedex.model.MockData

class RegionsActivity : BottomNavActivity(), OnRegionClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var listView = findViewById<RecyclerView>(R.id.regionsRecyclerView)
        listView.adapter = RegionAdapter(pkRegionList = MockData.regions, context = this,this)
    }

    override val contentViewId: Int
        get() = R.layout.activity_regions
    override val navigationMenuItemId: Int
        get() = R.id.navigation_regions

    override fun onRegionClick(region: PokemonRegion) {
        val intent = Intent(this, PokemonListActivity::class.java)
        intent.putExtra("regionId",region.id)
        startActivity(intent)
    }
}