package nseki.com.app.samplepagingroom.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import nseki.com.app.samplepagingroom.databinding.ItemRandomStringBinding
import nseki.com.app.samplepagingroom.domain.RandomString

class RandomStringAdapter(private val onClick: (RandomString) -> Unit) :
    PagingDataAdapter<RandomString, RandomStringViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: RandomStringViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomStringViewHolder {
        return RandomStringViewHolder(
            ItemRandomStringBinding.inflate(LayoutInflater.from(parent.context) ,parent, false),
            onClick
        )
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RandomString>() {
            override fun areItemsTheSame(oldItem: RandomString, newItem: RandomString): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: RandomString, newItem: RandomString): Boolean {
                return oldItem.string == newItem.string
            }
        }
    }
}

class RandomStringViewHolder(
    private val binding: ItemRandomStringBinding,
    private val onClick: (RandomString) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var item: RandomString? = null

    fun bind(item: RandomString?) {
        this.item = item ?: return
        binding.randomString.text = item.string
        binding.score.text = item.score.toString()

        binding.root.setOnClickListener {
            this.item?.let {
                onClick(it)
            }
        }
    }
}
