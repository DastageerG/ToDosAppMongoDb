package com.example.todosappmongodb.utils

import android.text.TextUtils

object Utils
{

    fun checkLoginFields(email:String,password:String) : Boolean
    {
        return  !(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
    } // checkLoginFields closed

    fun checkRegisterFields(name:String,email:String,password:String) : Boolean
    {
        return  !(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name))
    } // checkLoginFields closed




}