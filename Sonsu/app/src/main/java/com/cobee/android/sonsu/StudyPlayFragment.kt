package com.cobee.android.sonsu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cobee.android.sonsu.databinding.FragmentStudyplayBinding

class StudyPlayFragment : Fragment() {
    private var _binding: FragmentStudyplayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstantState: Bundle?
    ): View {
        _binding = FragmentStudyplayBinding.inflate(inflater, container, false)
        return binding.root
    }

}