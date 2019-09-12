package com.binarysages.mobile.app.corespirit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL


class LoginActivity : BaseActivity() {
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId in 1..2131165251) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private inner class Login : AsyncTask<String, Void, Boolean>() {
        private val progressBar: ProgressBar = findViewById(R.id.progressBarLogin)
        private val errMsg = findViewById<TextView>(R.id.loginErrMsg)

        override fun onPostExecute(result: Boolean?) {
            progressBar.visibility = ProgressBar.INVISIBLE
            if (result!!) {

            } else {
                Handler().postDelayed({
                    errMsg.visibility = TextView.INVISIBLE
                }, 5000)
                errMsg.text = "Incorrect login\\password"
                errMsg.visibility = TextView.VISIBLE
            }
        }

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = ProgressBar.VISIBLE
        }

        override fun doInBackground(vararg p0: String): Boolean? {
            val body = """
                {
                    "email":"${p0[0]}}",
                    "password":"${p0[1]}"
                }
            """.trimIndent()
            val resp = URL("https://corespirit.com/api/CoreUsers/login?include=user")
                .openConnection()
                .let {
                    it as HttpURLConnection
                }.apply {
                    setRequestProperty("Content-Type", "application/json; charset=utf-8")
                    requestMethod = "POST"
                    doOutput = true
                    val outputWriter = OutputStreamWriter(outputStream)
                    outputWriter.write(body)
                    outputWriter.flush()
                }.let {
                    if (it.responseCode == 200) it.inputStream else return false
                }.let {  streamToRead ->
                    BufferedReader(InputStreamReader(streamToRead)).use {
                        val response = StringBuffer()
                        var inputLine = it.readLine()
                        while (inputLine != null) {
                            response.append(inputLine)
                            inputLine = it.readLine()
                        }
                        it.close()
                        Log.d("RESPONSE>", it.javaClass.toString())
                        response.toString()
                    }
                }
            return true
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, R.layout.activity_login)
        val submitBtn = findViewById<Button>(R.id.sendCredentials)
        submitBtn.setOnClickListener {
            Login().execute(
                findViewById<EditText>(R.id.loginInput).text.toString(),
                findViewById<EditText>(R.id.passwordInput).text.toString()
            )
        }

    }
}
