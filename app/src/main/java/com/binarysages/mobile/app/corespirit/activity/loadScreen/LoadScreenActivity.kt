package com.binarysages.mobile.app.corespirit.activity.loadScreen

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.mainActivity.MainActivity
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import java.net.URL

class LoadScreenActivity : AppCompatActivity() {
    private lateinit var articles: Array<ArticleModel>
    private lateinit var logo: ImageView
    private lateinit var reloadButton: Button

    inner class LoadArticle : AsyncTask<Void, Void, Array<ArticleModel>>() {

        private val progressBar: ProgressBar = findViewById(R.id.loadScreenProgressBar)

        override fun onPostExecute(result: Array<ArticleModel>?) {
            super.onPostExecute(result)
            progressBar.visibility = ProgressBar.INVISIBLE
            if (result != null) {
                this@LoadScreenActivity.articles = result
                loadComplete()
            } else {
                reloadButton.visibility = Button.VISIBLE
                findViewById<TextView>(R.id.loadScreenErrorMsg).visibility = TextView.VISIBLE
            }
        }

        override fun onPreExecute() {
            val asyncObj = this
            Handler().postDelayed({
                if (asyncObj.status != Status.FINISHED) asyncObj.cancel(true)
            }, 10000)
            progressBar.visibility = ProgressBar.VISIBLE
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Void?): Array<ArticleModel> {
            val res =
                URL("https://corespirit.com/api/Articles/loadArticles?&skip=0").readText()
            return GsonBuilder()
                .serializeNulls()
                .create()
                .fromJson<Array<ArticleModel>>(res, Array<ArticleModel>::class.java)
        }
    }

    private fun reloadActivity() {
        finish()
        startActivity(intent)
    }

    private fun loadComplete() {
        val intent = Intent(this, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("articles", articles)
        intent.putExtra("BUNDLE", bundle)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        LoadArticle().execute()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_screen_activty)
        overridePendingTransition(
            R.anim.fadein,
            R.anim.fadeout
        )

//        find elements in this activity
        reloadButton = findViewById(R.id.reloadLoadScreenButton)
        logo = findViewById(R.id.loadScreenImage)

//        add visibility on button
        reloadButton.visibility = Button.INVISIBLE
//        set Logo
        Picasso.get().load(R.mipmap.logo).into(logo)
        reloadButton.setOnClickListener {
            reloadActivity()
        }
        reloadButton.visibility = Button.INVISIBLE
    }

}
