package com.seanshubin.dir.size.domain

import java.nio.file.Path

interface Notifications {
    fun consideringPath(path: Path, depth:Int)
}