package com.cobee.android.sonsu

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cobee.android.sonsu.databinding.ActivityStudyplayBinding


class StudyPlayActivity : AppCompatActivity() {

    lateinit var binding: ActivityStudyplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudyplayBinding.inflate(layoutInflater)

        val toolbarbodytemplate = binding.studyplayTb
        setSupportActionBar(toolbarbodytemplate)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val mVideoView = binding.studyPlayVideo
        val uri: Uri = Uri.parse("https://sonsu-s3-bucket.s3.ap-northeast-2.amazonaws.com/video/11001.mp4")
        mVideoView.setVideoURI(uri)

        mVideoView.setOnPreparedListener { mp -> // 준비 완료되면 비디오 재생
            mp.start()
        }

//        if (intent.hasExtra("arrListPlay")) {
//            val arrListPlay:ArrayList<String> = intent.getStringArrayListExtra("arrListPlay")!!
//            val stAdapter = StudyPlayAdapter(arrListPlay)
//            binding.spLinear.adapter = stAdapter
////        } else
//        if (intent.hasExtra("arrListNext")) {
//            val arrListNext:ArrayList<String> = intent.getStringArrayListExtra("arrListNext")!!
//            val wAdapter = WordAdapter(arrListNext)
//            binding.studyplayLv.adapter = wAdapter
//            Log.d("ha", "ha")
//        }

        val arrListNext: ArrayList<String> = arrayListOf()
        arrListNext.add("ㄴ(니은)")
        arrListNext.add("ㄷ(디귿)")
        arrListNext.add("ㅏ(아)")
        arrListNext.add("ㅓ(어)")
        arrListNext.add("ㅜ(우)")
        val wAdapter = WordAdapter(arrListNext)
        binding.studyplayLv.adapter = wAdapter

        setContentView(binding.root)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                //뒤로가기 버튼 눌렀을 때
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}