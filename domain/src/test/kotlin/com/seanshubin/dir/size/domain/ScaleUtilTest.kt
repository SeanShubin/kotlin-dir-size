package com.seanshubin.dir.size.domain

import kotlin.test.Test
import kotlin.test.assertEquals

class ScaleUtilTest {
    @Test
    fun parseAtScale(){
        //kmgtpezy
        val scaleUtil:ScaleUtil = HumanizedScaleUtil
        assertEquals(1, scaleUtil.parse("1"))
        assertEquals(1, scaleUtil.parse("1k"))
        assertEquals(1, scaleUtil.parse("1m"))
        assertEquals(1, scaleUtil.parse("1g"))
        assertEquals(1, scaleUtil.parse("1t"))
        assertEquals(1, scaleUtil.parse("1p"))
        assertEquals(1, scaleUtil.parse("1e"))
        assertEquals(1, scaleUtil.parse("1z"))
        assertEquals(1, scaleUtil.parse("1y"))

    }
}