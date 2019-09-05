package com.seanshubin.dir.size.domain

import com.seanshubin.dir.size.contract.FilesContract
import java.nio.file.Path
import kotlin.streams.toList

class TreeBuilderFromFiles(private val files:FilesContract,
                           private val consideringPath:(Path, Int)->Unit) :TreeBuilder {
    override fun buildTree(basePath: Path): Tree =
        buildTreeRecursive(basePath, depth = 0)

    private fun buildTreeRecursive(basePath: Path, depth:Int): Tree {
        consideringPath(basePath, depth)
        return try {
            when {
                files.isRegularFile(basePath) -> {
                    val size = files.size(basePath)
                    Leaf(Value(basePath, PathType.FILE, size))
                }
                files.isDirectory(basePath) -> {
                    val childPaths = files.list(basePath).use { it.toList() }
                    fun buildTreeFunction(path: Path): Tree = buildTreeRecursive(path, depth + 1)
                    val children = childPaths.map(::buildTreeFunction)
                    val size = children.map { it.value.size }.sum()
                    Branch(Value(basePath, PathType.DIRECTORY, size), children)
                }
                else -> Leaf(Value(basePath, PathType.UNABLE_TO_DETERMINE, 0))
            }
        } catch(_:java.nio.file.FileSystemException){
            Leaf(Value(basePath, PathType.FILE_SYSTEM_EXCEPTION, 0))
        }
    }
}