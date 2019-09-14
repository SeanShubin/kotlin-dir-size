package com.seanshubin.dir.size.domain

import java.lang.RuntimeException

object HumanizedLimitParser :LimitParser{
    private const val scales = "kmgtpezy" //kilo, mega, giga, tera, peta, exa, zetta, yotta
    private val regexes = listOf(Regex("""(\d+)""")) + scales.map { Regex("""(\d+)\s*$it""") }
    override fun parse(limitString: String): Long{
        var scale:Long = 1
        for (regex in regexes) {
            val result = regex.matchEntire(limitString)
            if(result == null){
                scale *= 1000
            } else {
                val group: MatchGroup = result.groups[1]!!
                return group.value.toLong() * scale
            }
        }
        throw RuntimeException("Unable to interpret limit '$limitString'")
    }
}
