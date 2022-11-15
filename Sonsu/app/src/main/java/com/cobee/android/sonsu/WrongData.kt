package com.cobee.android.sonsu

import com.google.gson.JsonArray

data class WrongData (
    var status: Int,
    var message: String,
    var data: JsonArray
    )