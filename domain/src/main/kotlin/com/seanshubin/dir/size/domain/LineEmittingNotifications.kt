package com.seanshubin.dir.size.domain

import java.nio.file.Path

class LineEmittingNotifications(private val emit:(String)->Unit) :Notifications{
    override fun consideringPath(path: Path, depth: Int) {
        if(depth < 3){
            emit("$path")
        }
    }
}