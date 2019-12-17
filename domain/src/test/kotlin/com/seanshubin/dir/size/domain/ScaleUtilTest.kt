package com.seanshubin.dir.size.domain

import kotlin.test.Test
import kotlin.test.assertEquals

class ScaleUtilTest {
    @Test
    fun parseAtScale() {
        val scaleUtil: ScaleUtil = HumanizedScaleUtil
        assertEquals(1, scaleUtil.parse("1"))
        assertEquals(1_024, scaleUtil.parse("1k"))
        assertEquals(1_048_576, scaleUtil.parse("1m"))
        assertEquals(1_073_741_824, scaleUtil.parse("1g"))
        assertEquals(1_099_511_627_776, scaleUtil.parse("1t"))
        assertEquals(1_125_899_906_842_624, scaleUtil.parse("1p"))
        assertEquals(1_152_921_504_606_846_976, scaleUtil.parse("1e"))
    }

    @Test
    fun approximate() {
        val scaleUtil: ScaleUtil = HumanizedScaleUtil
        assertEquals("1b", scaleUtil.approximate(1))
        assertEquals("1023b", scaleUtil.approximate(1023))
        assertEquals("1k", scaleUtil.approximate(1_024))
        assertEquals("1k", scaleUtil.approximate(1_025))
        assertEquals("1k", scaleUtil.approximate(2_047))
        assertEquals("2k", scaleUtil.approximate(2_048))
        assertEquals("2k", scaleUtil.approximate(2_049))
        assertEquals("1m", scaleUtil.approximate(1_048_576))
        assertEquals("1g", scaleUtil.approximate(1_073_741_824))
        assertEquals("1t", scaleUtil.approximate(1_099_511_627_776))
        assertEquals("1p", scaleUtil.approximate(1_125_899_906_842_624))
        assertEquals("1e", scaleUtil.approximate(1_152_921_504_606_846_976))
    }
}