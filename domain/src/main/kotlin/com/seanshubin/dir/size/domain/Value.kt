package com.seanshubin.dir.size.domain

import java.nio.file.Path
import java.util.Comparator

data class Value(val path: Path, val type:PathType, val size:Long){
    companion object{
        val descendingBySize = Comparator<Value> { first, second ->
            -first.size.compareTo(second.size)
        }
    }
}
