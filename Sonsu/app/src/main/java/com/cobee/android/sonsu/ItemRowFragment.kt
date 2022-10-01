package com.cobee.android.sonsu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.cobee.android.sonsu.databinding.ItemRowBinding

class ItemRowFragment : Fragment(), View.OnClickListener {
    private var _binding: ItemRowBinding? = null
    private val binding get() = _binding!!
    var mainActivity: MainActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstantState: Bundle?
    ): View {
        _binding = ItemRowBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        val btnSequence = binding.studylistConstlayout.children
        btnSequence.forEach { btn ->
            btn.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.word_name1 -> {
                Log.v("test", "hello?")
                mainActivity!!.openStudyPlayOnStudyList(1)
            }
        }
    }

//    companion object {
//        private const val TAG = "MainFragment"
//        fun instance() = MainFragment()
//    }

}