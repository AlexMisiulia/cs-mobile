package com.binarysages.mobile.app.corespirit

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private inner class Login : AsyncTask<Array<String>, Void, Boolean>() {
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

        override fun doInBackground(vararg p0: Array<String>?): Boolean? {
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val submitBtn = findViewById<Button>(R.id.sendCredentials)
        submitBtn.setOnClickListener {
            val login = findViewById<EditText>(R.id.loginInput).text.toString()
            val password = findViewById<EditText>(R.id.passwordInput).text.toString()
            Login().execute()
        }

    }
}
