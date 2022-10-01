package com.cobee.android.sonsu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ExpandableAdapter(
    private val levelList: List<LevelTitle>
) : RecyclerView.Adapter<ExpandableAdapter.MyViewHolder>() {

    class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(leveltitle: LevelTitle) {
            val constlayout = itemView.findViewById<ConstraintLayout>(R.id.studylist_constlayout)
            val txtName = itemView.findViewById<TextView>(R.id.txt_name)
            val imgMore = itemView.findViewById<ImageButton>(R.id.img_more)
            val layoutExpand = itemView.findViewById<LinearLayout>(R.id.layout_expand)

            txtName.text = leveltitle.title

            imgMore.setOnClickListener {
                val show = toggleLayout(!leveltitle.isExpanded, it, layoutExpand, constlayout)
                leveltitle.isExpanded = show

            }

        }

        private fun toggleLayout(
            isExpanded: Boolean,
            view: View,
            layoutExpand: LinearLayout,
            constlayout: ConstraintLayout
        ): Boolean {
            ToggleAnimation.toggleArrow(view, isExpanded)
            if (isExpanded) {
                ToggleAnimation.expand(layoutExpand)
                constlayout.setBackgroundResource(R.drawable.layerlist_constlayout_studylist_checked)
            } else {
                ToggleAnimation.collapse(layoutExpand)
                constlayout.setBackgroundResource(R.drawable.layerlist_constlayout_studylist_unchecked)
            }
            return isExpanded
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(levelList[position])
    }

    override fun getItemCount(): Int {
        return levelList.size
    }

}