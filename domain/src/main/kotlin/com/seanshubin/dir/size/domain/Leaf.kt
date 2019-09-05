package com.seanshubin.dir.size.domain

data class Leaf(override val value: Value):Tree {
    override fun toList(): List<Value> = listOf(value)
}
