package com.cobee.android.sonsu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.cobee.android.sonsu.databinding.FragmentStudyBinding

class StudyFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentStudyBinding? = null
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
        _binding = FragmentStudyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        val btnSequence = binding.studyFrame.children
        btnSequence.forEach { btn ->
            btn.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.study_btn_1 -> {
                mainActivity!!.openStudyListOnStudy(1)
            }
            R.id.study_btn_2 -> {
                mainActivity!!.openStudyListOnStudy(2)
            }
            R.id.study_btn_3 -> {
                mainActivity!!.openStudyListOnStudy(3)
            }
        }
    }

//    companion object {
//        private const val TAG = "MainFragment"
//        fun instance() = MainFragment()
//    }

}