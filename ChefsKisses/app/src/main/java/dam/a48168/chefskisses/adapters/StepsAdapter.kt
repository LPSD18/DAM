package dam.a48168.chefskisses.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dam.a48168.chefskisses.databinding.ItemStepsBinding

class StepsAdapter : RecyclerView.Adapter<StepsAdapter.ViewHolder>() {

    private var steps: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStepsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val step = steps[position]
        holder.bind(step, position + 1) // position + 1 to start numbering from 1
    }

    override fun getItemCount(): Int {
        return steps.size
    }

    fun submitList(list: List<String>) {
        steps = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemStepsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(step: String, stepNumber: Int) {
            binding.step = step
            binding.stepNumber = stepNumber
            binding.executePendingBindings()
        }
    }
}
