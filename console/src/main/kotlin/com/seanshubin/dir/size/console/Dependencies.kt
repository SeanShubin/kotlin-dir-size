package com.seanshubin.dir.size.console

import com.seanshubin.dir.size.contract.FilesContract
import com.seanshubin.dir.size.contract.FilesDelegate
import com.seanshubin.dir.size.domain.*
import com.seanshubin.dir.size.logger.LogGroup
import com.seanshubin.dir.size.logger.LoggerFactory
import com.seanshubin.dir.size.table.RowStyleTableFormatter
import com.seanshubin.dir.size.table.TableFormatter
import java.nio.file.Path
import java.nio.file.Paths

class Dependencies(args:Array<String>){
    private val baseDir: Path = Paths.get(args[0])
    private val limitString = args[1]
    private val logPath:Path = Paths.get("out", "log")
    private val logGroup:LogGroup = LoggerFactory.instanceDefaultZone.createLogGroup(logPath)
    private val statusLogger = logGroup.create("status")
    private val reportLogger = logGroup.create("report")
    private val files:FilesContract = FilesDelegate
    private val emitStatus:(String)->Unit = statusLogger::log
    private val notifications: Notifications = LineEmittingNotifications(emitStatus)
    private val treeBuilder:TreeBuilder = TreeBuilderFromFiles(files, notifications::consideringPath)
    private val tableFormatter:TableFormatter = RowStyleTableFormatter.boxDrawing
    private val emitReportLine:(String)->Unit=reportLogger::log
    private val limitParser:LimitParser = HumanizedLimitParser
    val runner:Runnable = Runner(
            baseDir, limitString, limitParser, treeBuilder, tableFormatter, emitReportLine)
}
