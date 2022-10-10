package com.cobee.android.sonsu

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cobee.android.sonsu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val fragmentStudyList = StudyListFragment()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.main_fl, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBottomNav.itemIconTintList = null // 아이콘의 원래 색을 찾아줌
        binding.mainBottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.tab_study -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fl, StudyFragment())
                        .commitAllowingStateLoss()
                    binding.frameLayoutStudyList.visibility = View.VISIBLE
                    return@setOnItemSelectedListener true
                }
                R.id.tab_wrong -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fl, WrongFragment())
                        .commitAllowingStateLoss()
                    binding.frameLayoutStudyList.visibility = View.INVISIBLE
                    return@setOnItemSelectedListener true
                }
                R.id.tab_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fl, HomeFragment())
                        .commitAllowingStateLoss()
                    binding.frameLayoutStudyList.visibility = View.INVISIBLE

                    return@setOnItemSelectedListener true
                }
                R.id.tab_dictionary -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, DictionaryFragment()).commitAllowingStateLoss()
                    binding.frameLayoutStudyList.visibility = View.INVISIBLE

                    return@setOnItemSelectedListener true
                }
                R.id.tab_mypage -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, MyPageFragment()).commitAllowingStateLoss()
                    binding.frameLayoutStudyList.visibility = View.INVISIBLE

                    return@setOnItemSelectedListener true
                }
                else -> {
                    false
                }
            }
        }
    }

    fun openStudyListOnStudy(int: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when (int) {
            1 -> transaction.replace(R.id.frameLayoutStudyList, fragmentStudyList)
            2 -> transaction.replace(R.id.frameLayoutStudyList, fragmentStudyList)
            3 -> transaction.replace(R.id.frameLayoutStudyList, fragmentStudyList)
        }
        transaction.commit()
    }

    fun openStudyPlayOnStudyList(int: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when (int) {
            1 -> transaction.replace(R.id.main_fl, StudyPlayFragment())
        }
        transaction.commit()
    }


}