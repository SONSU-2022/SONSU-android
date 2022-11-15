package com.cobee.android.sonsu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import com.cobee.android.sonsu.databinding.FragmentWrongBinding
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class WrongFragment : Fragment() {
    private var _binding: FragmentWrongBinding? = null
    private val binding get() = _binding!!
    lateinit var calendarView: CalendarView
    var mainActivity: MainActivity? = null
//    lateinit var adapter: WordAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstantState: Bundle?
    ): View {
        _binding = FragmentWrongBinding.inflate(inflater, container, false)
        calendarView = binding.calendarView

        val cal = Calendar.getInstance()
//        val year = cal.get(Calendar.YEAR).toString()
        val month = (cal.get(Calendar.MONTH) + 1).toString()
        val day = cal.get(Calendar.DATE).toString()
        val week = cal.get(Calendar.DAY_OF_WEEK).toString()

        fun changeweek(week: String?): String{
            when (week) {
                "1" -> return "일요일"
                "2" -> return "월요일"
                "3" -> return "화요일"
                "4" -> return "수요일"
                "5" -> return "목요일"
                "6" -> return "금요일"
                "7" -> return "토요일"
                else -> return "error"
            }
        }

        val Date = (month + "월 " + day + "일 " + changeweek(week))

//        binding.wrongTvYear.text =  year
        binding.wrongTvDay.text = Date

        calendarView.setOnDateChangeListener(CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->

            val calendar = Calendar.getInstance()
            calendar[year, month] = dayOfMonth
            val week: String = calendar.get(Calendar.DAY_OF_WEEK).toString()

            val Date = ((month+1).toString() + "월 " + dayOfMonth + "일 " + changeweek(week))

            Log.d ("result", "날짜" + Date)

//            binding.wrongTvYear.text = year.toString()
            binding.wrongTvDay.text = Date

            val retrofit = Retrofit.Builder().baseUrl("http://172.20.10.2:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();
            val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java);

            retrofitService.getWrongData(year, month+1, dayOfMonth)?.enqueue(object: Callback<WrongData> {
                override fun onResponse(call: Call<WrongData>, response: Response<WrongData>) {
                    if(response.isSuccessful){
                        var result: WrongData = response.body()!!
                        val jsonString = result.data.toString()
                        val jArray = JSONArray(jsonString)
                        val arrList : ArrayList<String> = arrayListOf()

                        for (i in 0 until jArray.length()) {
                            val obj = jArray.getJSONObject(i)
                            val wordIdx = obj.getInt("wordIdx")
                            val wordName = obj.getString("wordName")
                            arrList.add(wordName)
                            val level = obj.getInt("level")
//                            Log.d("test", "title($i): $wordIdx")
//                            Log.d("test", "url($i): $wordName")
//                            Log.d("test", "draft($i): $level")
                        }
                        Log.d ("result", "성공" + arrList)

                        val wAdapter = WordAdapter(arrList)
                        binding.wrongLv.adapter = wAdapter

                    } else {
                        Log.d ("result", "실패")
                    }
                }

                override fun onFailure(call: Call<WrongData>, t: Throwable) {
                    Log.d("result", "onFailure 에러: " + t.message.toString());
                }
            })
        })

        binding.wrongLv.setOnItemClickListener {parent, view, position, id ->
            mainActivity!!.openStudyPlayOnStudyList(0)
            Log.d("hmm", position.toString())
        }

        return binding.root
    }


}
