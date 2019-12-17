package com.seanshubin.dir.size.domain

object HumanizedScaleUtil : ScaleUtil {
    private const val scaleSize = 1024
    private const val scales = "bkmgtpe" //kilo, mega, giga, tera, peta, exa
    private val unscaledRegex = Regex("""(\d+)""")
    private val regexes = scales.map { Regex("""(\d+)\s*$it""") }
    override fun parse(limitString: String): Long {
        var scale: Long = 1
        val unscaledResult = unscaledRegex.matchEntire(limitString)
        if (unscaledResult == null) {
            for (regex in regexes) {
                val result = regex.matchEntire(limitString)
                if (result == null) {
                    scale *= scaleSize
                } else {
                    val group: MatchGroup = result.groups[1]!!
                    return group.value.toLong() * scale
                }
            }
        } else {
            val group: MatchGroup = unscaledResult.groups[1]!!
            return group.value.toLong() * scale
        }
        throw RuntimeException("Unable to interpret limit '$limitString'")
    }

    override fun approximate(value: Long): String {
        var approximation = value
        var index = 0
        while (approximation >= scaleSize) {
            index++
            approximation /= scaleSize
        }
        return "$approximation${scales[index]}"
    }
}
