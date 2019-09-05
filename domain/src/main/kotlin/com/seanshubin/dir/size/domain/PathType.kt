package com.seanshubin.dir.size.domain

enum class PathType{
    DIRECTORY,
    FILE,
    SYMBOLIC_LINK,
    UNABLE_TO_DETERMINE,
    FILE_SYSTEM_EXCEPTION
}