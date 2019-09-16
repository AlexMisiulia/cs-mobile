package com.binarysages.mobile.app.corespirit


class LoginActivity : BaseActivity() {
//    private var menu: Menu? = null
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId in 1..2131165251) {
//            startActivity(Intent(applicationContext, MainActivity::class.java))
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        this.menu = menu
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    private inner class Login : AsyncTask<String, Void, UserLogginedModel?>() {
//        private val progressBar: ProgressBar = findViewById(R.id.progressBarLogin)
//        private val errMsg = findViewById<TextView>(R.id.loginErrMsg)
//        private var user: UserLogginedModel? = null
//
//        override fun onPostExecute(result: UserLogginedModel?) {
//            progressBar.visibility = ProgressBar.INVISIBLE
//
//            result?.let {
//                if (result) {
//                    user?.let {
//                        it.practitioner?.let {
//                            generateUserMenu(menu, "practitioner", this@LoginActivity)
//                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                            return
//                        }
//                        generateUserMenu(menu, "user", this@LoginActivity)
//                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                    }
//                } else {
//                    Handler().postDelayed({
//                        errMsg.visibility = TextView.INVISIBLE
//                    }, 5000)
//                    errMsg.text = "Incorrect login\\password"
//                    errMsg.visibility = TextView.VISIBLE
//                }
//            }
//        }
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            progressBar.visibility = ProgressBar.VISIBLE
//        }
//
//        override fun doInBackground(vararg p0: String): UserLogginedModel? {
//            Log.d("RESP>>>", "SUBMIT")
//            val body = """
//                {
//                    "email":"${p0[0]}",
//                    "password":"${p0[1]}"
//                }
//            """.trimIndent()
//            user = Gson().fromJson(
//                REQUESTS.doPost(
//                    url = "https://corespirit.com/api/CoreUsers/login?include=user",
//                    body = body
//                ), UserLogginedModel::class.java
//            )
//            return user
//        }
//    }
//
//    @SuppressLint("MissingSuperCall")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState, R.layout.activity_login)
//        val submitBtn = findViewById<Button>(R.id.sendCredentials)
//        submitBtn.setOnClickListener {
//            Log.d("RESP>>>", "CLICKED")
//            Login().execute(
//                findViewById<EditText>(R.id.loginInput).text.toString(),
//                findViewById<EditText>(R.id.passwordInput).text.toString()
//            )
//        }
//
//    }
}
