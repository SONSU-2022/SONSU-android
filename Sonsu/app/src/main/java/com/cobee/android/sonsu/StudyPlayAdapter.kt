package com.cobee.android.sonsu

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class StudyPlayAdapter(val DataList: ArrayList<String>) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return DataList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View? {
        var view = converView
        val context = parent?.context

        if(view == null) {
            val vi = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = vi.inflate(R.layout.activity_studyplay, parent, false)
        }

//        val textView1 = view?.findViewById(R.id.textView1) as TextView
//        textView1.text = DataList[position]
//        val textView2 = view?.findViewById(R.id.textView2) as TextView
//        textView2.text = DataList[position]

        Log.d("DataList", DataList[position])


        return view
    }

    override fun getCount(): Int {
        return DataList.size
    }
}