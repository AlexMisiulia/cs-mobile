package com.binarysages.mobile.app.corespirit.network

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

val REQUESTS = CSRequests()

class CSRequests {
    fun doPost(url: String, body: String? = null): String {
        URL(url)
            .openConnection()
            .let {
                it as HttpURLConnection
            }.apply {
                setRequestProperty("Content-Type", "application/json; charset=utf-8")
                requestMethod = "POST"
                doOutput = true
                val outputWriter = OutputStreamWriter(outputStream)
                outputWriter.write(body.toString())
                outputWriter.flush()
            }.let {
                if (it.responseCode in 200..201) it.inputStream else it.errorStream
            }.let { streamToRead ->
                BufferedReader(InputStreamReader(streamToRead)).use {
                    val response = StringBuffer()
                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()
                    return response.toString()
                }
            }
    }
}