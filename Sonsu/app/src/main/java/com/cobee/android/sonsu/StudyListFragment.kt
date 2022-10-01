package com.cobee.android.sonsu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cobee.android.sonsu.databinding.FragmentStudylistBinding

class StudyListFragment : Fragment() {
    private var _binding: FragmentStudylistBinding? = null
    private val binding get() = _binding!!
    private lateinit var levelTitle: List<LevelTitle>
    private lateinit var wordTitle: List<WordTitle>
    private lateinit var adapter: ExpandableAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstantState: Bundle?
    ): View {
        _binding = FragmentStudylistBinding.inflate(inflater, container, false)

        levelTitle = ArrayList()
        levelTitle = loadLevelData()
        wordTitle = ArrayList()
        wordTitle = loadWordData()

        binding.recyclerList.setHasFixedSize(true)
        adapter = ExpandableAdapter(levelTitle)
        binding.recyclerList.adapter = adapter

        return binding.root
    }

    private fun loadLevelData(): List<LevelTitle> {
        val level = ArrayList<LevelTitle>()

        val curri1 = resources.getStringArray(R.array.curri1)

        for (i in curri1.indices) {
            val levelTitle = LevelTitle().apply {
                title = curri1[i]
            }
            level.add(levelTitle)
        }
        return level
    }

    private fun loadWordData(): List<WordTitle> {
        val word = ArrayList<WordTitle>()

        val word1 = resources.getStringArray(R.array.word1)

        for (i in word1.indices) {
            val wordTitle = WordTitle().apply {
                title = word1[i]
            }
            word.add(wordTitle)
        }
        return word
    }

}