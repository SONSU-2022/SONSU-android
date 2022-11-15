package com.cobee.android.sonsu

import com.google.gson.JsonArray

data class StudyData (
    var status: Int,
    var message: String,
    var data: JsonArray
    )