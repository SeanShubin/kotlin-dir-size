package com.seanshubin.dir.size.domain

import com.seanshubin.dir.size.table.TableFormatter
import com.seanshubin.dir.size.table.TableFormatter.Justify.Left
import com.seanshubin.dir.size.table.TableFormatter.Justify.Right
import java.nio.file.Path

class Runner(private val baseDir: Path,
             private val limitString:String,
             private val scaleUtil:ScaleUtil,
             private val treeBuilder: TreeBuilder,
             private val tableFormatter:TableFormatter,
             private val emitReportLine:(String)->Unit) :Runnable{
    override fun run() {
        val limit = scaleUtil.parse(limitString)
        val tree = treeBuilder.buildTree(baseDir)
        val list = tree.toList().filter {it.size >= limit}.sortedWith(Value.descendingBySize)
        val rows = list.map(::toRow)
        val tableRows = tableFormatter.format(listOf(rowHeaders) + rows)
        tableRows.forEach(emitReportLine)
    }

    private val rowHeaders = listOf("path", "type", "size")
    private fun toRow(value:Value):List<Any> = listOf(
            Left(value.path),
            Left(value.type),
            Right(String.format("%,d", value.size)))
}
