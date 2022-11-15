package com.cobee.android.sonsu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.cobee.android.sonsu.databinding.FragmentStudyBinding
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

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

        binding.studyLv.setOnItemClickListener {parent, view, position, id ->
            mainActivity!!.openStudyPlayOnStudyList(0)
            Log.d("hmm", position.toString())
        }

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
        val retrofit = Retrofit.Builder().baseUrl("http://172.20.10.2:8080/")
            .addConverterFactory(GsonConverterFactory.create()).build();
        val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java);

        when (v.id) {
            R.id.study_btn_1 -> {
                retrofitService.getStudyData(1)?.enqueue(object: Callback<StudyData>{
                    override fun onResponse(call: Call<StudyData>, response: Response<StudyData>) {
                        if(response.isSuccessful){
                            var result: StudyData = response.body()!!
                            val jsonString = result.data.toString()
                            val jArray = JSONArray(jsonString)
                            val arrList : ArrayList<String> = arrayListOf()

                            for (i in 0 until jArray.length()) {
                                val obj = jArray.getJSONObject(i)
                                val wordsDto = obj.getJSONArray("wordsDto")
                                for (j in 0 until wordsDto.length()){
                                    val dtoObj = wordsDto.getJSONObject(j)
                                    val wordIdx = dtoObj.getInt("wordIdx")
                                    val wordName = dtoObj.getString("wordName")
                                    arrList.add(wordName)
                                    Log.d("test", "url($i): $wordName")
                                }
                            }
                            Log.d ("result", "성공" + arrList)
                            val wAdapter = WordAdapter(arrList)
                            binding.studyLv.adapter = wAdapter
                        } else {
                            Log.d ("result", "실패")
                        }
                    }
                    override fun onFailure(call: Call<StudyData>, t: Throwable) {
                        Log.d("result", "onFailure 에러: " + t.message.toString());
                    }
                })
            }
            R.id.study_btn_2 -> {
                retrofitService.getStudyData(2)?.enqueue(object: Callback<StudyData>{
                    override fun onResponse(call: Call<StudyData>, response: Response<StudyData>) {
                        if(response.isSuccessful){
                            var result: StudyData = response.body()!!
                            val jsonString = result.data.toString()
                            val jArray = JSONArray(jsonString)
                            val arrList : ArrayList<String> = arrayListOf()

                            for (i in 0 until jArray.length()) {
                                val obj = jArray.getJSONObject(i)
                                val wordsDto = obj.getJSONArray("wordsDto")

                                for (j in 0 until wordsDto.length()){
                                    val dtoObj = wordsDto.getJSONObject(j)
                                    val wordIdx = dtoObj.getInt("wordIdx")
                                    val wordName = dtoObj.getString("wordName")
                                    arrList.add(wordName)
                                    Log.d("test", "url($i): $wordName")
                                }
                            }
                            Log.d ("result", "성공" + arrList)
                            val wAdapter = WordAdapter(arrList)
                            binding.studyLv.adapter = wAdapter
                        } else {
                            Log.d ("result", "실패")
                        }
                    }
                    override fun onFailure(call: Call<StudyData>, t: Throwable) {
                        Log.d("result", "onFailure 에러: " + t.message.toString());
                    }
                })
            }
            R.id.study_btn_3 -> {
                retrofitService.getStudyData(3)?.enqueue(object: Callback<StudyData>{
                    override fun onResponse(call: Call<StudyData>, response: Response<StudyData>) {
                        if(response.isSuccessful){
                            var result: StudyData = response.body()!!
                            val jsonString = result.data.toString()
                            val jArray = JSONArray(jsonString)
                            val arrList : ArrayList<String> = arrayListOf()

                            for (i in 0 until jArray.length()) {
                                val obj = jArray.getJSONObject(i)
                                val wordsDto = obj.getJSONArray("wordsDto")

                                for (j in 0 until wordsDto.length()){
                                    val dtoObj = wordsDto.getJSONObject(j)
                                    val wordIdx = dtoObj.getInt("wordIdx")
                                    val wordName = dtoObj.getString("wordName")
                                    arrList.add(wordName)
                                    Log.d("test", "url($i): $wordName")
                                }
                            }
                            Log.d ("result", "성공" + arrList)
                            val wAdapter = WordAdapter(arrList)
                            binding.studyLv.adapter = wAdapter
                        } else {
                            Log.d ("result", "실패")
                        }
                    }
                    override fun onFailure(call: Call<StudyData>, t: Throwable) {
                        Log.d("result", "onFailure 에러: " + t.message.toString());
                    }
                })
            }
        }
    }

}
