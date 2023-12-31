package com.ancient.game.domain.ancient_game.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ancient.game.R
import com.ancient.game.databinding.ItemAncientGameBinding

class AncientGameAdapter(private var itemClicked: (Int) -> Unit) : RecyclerView.Adapter<GameViewHolder>() {
    var items = mutableListOf<GameItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            ItemAncientGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemClicked = itemClicked
    }

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }
}

class GameViewHolder(private val binding: ItemAncientGameBinding) : RecyclerView.ViewHolder(binding.root) {
    var itemClicked: ((Int) -> Unit)? = null
    fun bind(item: GameItem) {
        val itemImg = when (item.imgValue) {
            1 -> R.drawable.symbol01
            2 -> R.drawable.symbol02
            3 -> R.drawable.symbol03
            4 -> R.drawable.symbol04
            5 -> R.drawable.symbol05
            6 -> R.drawable.symbol06
            7 -> R.drawable.symbol07
            8 -> R.drawable.symbol08
            else -> null
        }
        if (itemImg != null) {
            binding.symbol.setImageResource(itemImg)
        } else {
            binding.symbol.setImageDrawable(null)
        }
        if (item.isSelected) {
            binding.symbol.setBackgroundResource(R.drawable.bg_selected)
        } else {
            binding.symbol.setBackgroundDrawable(null)
        }
        binding.root.setOnClickListener {
            if (item.imgValue != null) {
                itemClicked?.invoke(adapterPosition)
            }
        }
    }
}