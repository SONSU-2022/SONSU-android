package com.cobee.android.sonsu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class WordAdapter(val WordList: ArrayList<String>) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return WordList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View? {
        var view = converView
        val context = parent?.context

        if(view == null) {
            val vi = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = vi.inflate(R.layout.item_word, parent, false)
        }

        val tv_wordname = view?.findViewById(R.id.tv_wordname) as TextView
        tv_wordname.text = WordList[position]
        return view
    }

    override fun getCount(): Int {
        return WordList.size
    }
}