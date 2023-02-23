package com.example.final_project.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.final_project.databinding.ItemTourismMenuBinding
import com.example.final_project.models.MenuTourism
import com.example.final_project.ui.activities.TypeBasedTourismActivity

class MenuTourismAdapter(private val menu_tourism: ArrayList<MenuTourism>): RecyclerView.Adapter<MenuTourismAdapter.MenuViewHolder>() {
    inner class MenuViewHolder(val binding: ItemTourismMenuBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemTourismMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        with(holder) {
            with(menu_tourism[position]) {
                Glide.with(itemView.context).load(this.image).into(binding.menuIllustration)
                binding.menuLabel.text = this.title

                holder.itemView.setOnClickListener {
                    val intent = Intent(it.context, TypeBasedTourismActivity::class.java)

                    intent.putExtra("tourismType", this.title)
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return menu_tourism.size
    }
}