package com.seanshubin.dir.size.domain

import java.nio.file.Path

interface TreeBuilder {
    fun buildTree(basePath: Path):Tree
}
