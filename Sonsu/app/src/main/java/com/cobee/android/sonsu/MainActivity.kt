package com.cobee.android.sonsu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cobee.android.sonsu.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

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
                    return@setOnItemSelectedListener true
                }
                R.id.tab_wrong -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fl, WrongFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.tab_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_fl, HomeFragment())
                        .commitAllowingStateLoss()

                    return@setOnItemSelectedListener true
                }
                R.id.tab_dictionary -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, DictionaryFragment()).commitAllowingStateLoss()

                    return@setOnItemSelectedListener true
                }
                R.id.tab_mypage -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, MyPageFragment()).commitAllowingStateLoss()

                    return@setOnItemSelectedListener true
                }
                else -> {
                    false
                }
            }
        }
    }

    fun openStudyPlayOnStudyList(int: Int) {
        val Intent = Intent(this, StudyPlayActivity::class.java)
        val retrofit = Retrofit.Builder().baseUrl("http://192.168.11.153:8080/")
            .addConverterFactory(GsonConverterFactory.create()).build();
        val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java);

        retrofitService.getStudyPlayData(int+1)?.enqueue(object: Callback<StudyPlayData> {
            override fun onResponse(call: Call<StudyPlayData>, response: Response<StudyPlayData>) {
                if(response.isSuccessful){
                    var result: StudyPlayData = response.body()!!
                    val jsonString = result.data.toString()
                    val obj = JSONObject(jsonString)
                    val arrListPlay : ArrayList<String> = arrayListOf()
                    val arrListNext : ArrayList<String> = arrayListOf()

                    for (i in 0 until obj.length()) {
                        val wordName = obj.getString("wordName")
                        arrListPlay.add(wordName)
                        val wordGesture = obj.getString("wordGesture")
                        arrListPlay.add(wordGesture)
                        val videoUrl = obj.getString("videoUrl")
                        arrListPlay.add(videoUrl)
                        val wordsDto = obj.getJSONArray("wordsDto")
                        for (j in 0 until wordsDto.length()){
                            val dtoObj = wordsDto.getJSONObject(j)
                            val wordIdx = dtoObj.getInt("wordIdx")
                            val wordName = dtoObj.getString("wordName")
                            arrListNext.add(wordName)
                            Log.d("test", "url($i): $wordName")
                        }
                    }
                    Log.d ("result", "성공" + arrListNext)
//                    Intent.putExtra("arrListPlay", arrListPlay)
//                    Intent.putExtra("arrListNext", arrListNext)
//                    val stAdapter = StudyPlayAdapter(arrListPlay)
//                    StudyPlayActivity.adapter = stAdapter
//                    binding.studyLv.adapter = wAdapter
                } else {
                    Log.d ("result", "실패")
                }
            }
            override fun onFailure(call: Call<StudyPlayData>, t: Throwable) {
                Log.d("result", "onFailure 에러: " + t.message.toString());
            }
        })
        startActivity(Intent)

    }


}