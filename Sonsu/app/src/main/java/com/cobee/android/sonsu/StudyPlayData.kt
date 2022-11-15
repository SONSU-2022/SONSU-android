package com.cobee.android.sonsu

import com.google.gson.JsonObject

data class StudyPlayData (
    var status: Int,
    var message: String,
    var data: JsonObject
    )