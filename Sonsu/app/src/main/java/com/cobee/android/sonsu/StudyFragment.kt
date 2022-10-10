package com.cobee.android.sonsu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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

        (activity as AppCompatActivity).setSupportActionBar(binding.studyToolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    //item 버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                //뒤로가기 버튼 눌렀을 때
                startActivity(Intent(context, HomeFragment::class.java))
//                (activity as AppCompatActivity).finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
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