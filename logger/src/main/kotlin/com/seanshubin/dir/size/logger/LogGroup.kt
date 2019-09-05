package com.seanshubin.dir.size.logger

import com.seanshubin.dir.size.contract.FilesContract
import java.nio.file.Path

class LogGroup(
        private val emit: (String) -> Unit,
        private val files: FilesContract,
        private val baseDir: Path) {
    fun create(name: String): Logger {
        files.createDirectories(baseDir)
        return ConsoleAndFileLogger(emit, files, baseDir.resolve("$name.log"))
    }
}
