package com.example.rumahobat_.activity.ui_apoteker.coba

import com.google.gson.annotations.SerializedName

internal class ServerResponse {
    // variable name should be same as in the json response from php
    @SerializedName("success")
    var success = false

    @SerializedName("message")
    var message: String? = null

}