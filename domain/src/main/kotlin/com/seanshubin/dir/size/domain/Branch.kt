package com.seanshubin.dir.size.domain

data class Branch(override val value: Value, val children:List<Tree>):Tree {
    override fun toList(): List<Value> =
        listOf(value) + children.flatMap { it.toList() }
}
