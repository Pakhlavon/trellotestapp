package com.app.trello.view.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.trello.R
import com.app.trello.databinding.ItemBoardBinding
import com.app.trello.databinding.ProductsItemBinding
import com.app.trello.utils.SharedPref
import com.app.trello.utils.loadImageFromGlide
import com.bumptech.glide.Glide
import javax.inject.Inject

class BoardItemAdapter @Inject constructor(private var list : ArrayList<String>)
    : RecyclerView.Adapter<BoardItemAdapter.ViewHolder>()
{
    private var onClickListener : OnClickListener? = null


    inner class ViewHolder(val binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        println(model)
        holder.binding.apply {
      itemBoardName.text = model
      itemBoardCreatedBy.text = SharedPref.user_name
        }

        holder.itemView.setOnClickListener {
            if(onClickListener != null){
                onClickListener!!.onClick(position,model)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun setOnClickListener(onClickListener : OnClickListener){
        this.onClickListener = onClickListener

    }

    interface OnClickListener{
        fun onClick(position : Int, model : String)
    }
}