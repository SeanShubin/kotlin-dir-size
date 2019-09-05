package com.seanshubin.dir.size.logger

interface Logger {
    fun log(line: String) {
        log(listOf(line))
    }

    fun log(lines: List<String>)
}
