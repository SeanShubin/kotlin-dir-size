package com.seanshubin.dir.size.domain

interface ScaleUtil {
    fun parse(limitString:String):Long
    fun approximate(value:Long):String
}