package com.seanshubin.dir.size.domain

interface LimitParser {
    fun parse(limitString:String):Long
}