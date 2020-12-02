package util

import java.io.File

class FileReader {
    fun read(filename: String): List<String> {
        val file = File(filename)
        return file.readLines()
    }
}