package dam.a48168.pokedex.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import dam.a48168.pokedex.R
import dam.a48168.pokedex.data.PokemonRegion
import dam.a48168.pokedex.databinding.ActivityRegionsBinding
import dam.a48168.pokedex.domain.DBModule
import dam.a48168.pokedex.model.MockData

class RegionsActivity : BottomNavActivity(), OnRegionClickListener {

    val viewModel: RegionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("qqqq")
        val regionBinding = binding as ActivityRegionsBinding
        var listView = regionBinding.regionsRecyclerView

        viewModel.initViewMode(DBModule.getInstance(this).regionRepository)

        viewModel.regions.observe(this) {
            listView.adapter = it?.let { it1 ->
                RegionAdapter(
                    pkRegionList = it1,this,this
                )
            }
        }

        viewModel.fetchRegions()
//        listView.adapter = RegionAdapter(pkRegionList = MockData.regions, context = this,this)
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

    override fun invoke(region: PokemonRegion) {
        val intent = Intent(this, PokemonListActivity::class.java)
        intent.putExtra("region",region)
        println("bora"+region.name)

        println("cccc")
        startActivity(intent)
    }
}