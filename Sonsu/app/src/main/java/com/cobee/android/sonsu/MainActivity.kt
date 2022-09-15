package com.cobee.android.sonsu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cobee.android.sonsu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.main_fl, HomeFragment()).commitAllowingStateLoss()

        binding.mainBottomNav.itemIconTintList = null // 아이콘의 원래 색을 찾아줌
        binding.mainBottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.tab_study -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fl, StudyFragment()).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.tab_wrong -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fl, WrongFragment()).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.tab_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fl, HomeFragment()).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.tab_dictionary -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fl, DictionaryFragment()).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.tab_mypage-> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fl, MyPageFragment()).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                else -> {
                    false
                }
            }
        }
    }
}