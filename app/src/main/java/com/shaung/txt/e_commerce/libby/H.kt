package com.shaung.txt.e_commerce.libby

import android.util.Log

class H {
    companion object {
        var USER_TOKEN = ""

        fun L (msg : String){
            Log.d("my_message", msg)
        }

        fun userAuth () : Boolean{
            return USER_TOKEN == ""
        }
    }
}