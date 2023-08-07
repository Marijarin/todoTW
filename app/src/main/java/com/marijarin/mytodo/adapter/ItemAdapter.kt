package com.marijarin.mytodo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marijarin.mytodo.R
import com.marijarin.mytodo.databinding.CardItemBinding
import com.marijarin.mytodo.databinding.CardItemBinding.inflate
import com.marijarin.mytodo.dto.Item
import com.marijarin.mytodo.util.AndroidUtils


interface OnItemListener {
    fun onRemove(item: Item) {}
}

class ItemAdapter(
    private val onItemListener: OnItemListener
) : ListAdapter<Item, ItemViewHolder>(ItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(onItemListener, binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


}

class ItemViewHolder(
    private val onItemListener: OnItemListener,
    private val binding: CardItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
        binding.apply {
            date.text = AndroidUtils.toDateFromLong(item.date)
            title.text = item.title
            desc.text = item.desc

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_item)
                    setOnMenuItemClickListener { it ->
                        when (it.itemId) {
                            R.id.remove -> {
                                onItemListener.onRemove(item)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }

}

class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

}