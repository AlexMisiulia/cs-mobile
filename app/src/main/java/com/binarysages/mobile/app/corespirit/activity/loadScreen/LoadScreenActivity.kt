package com.binarysages.mobile.app.corespirit.activity.loadScreen

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.mainActivity.MainActivity
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_load_screen_activty.*
import java.net.URL

class LoadScreenActivity : AppCompatActivity() {
    private lateinit var articles: Array<ArticleModel>

    inner class LoadArticle : AsyncTask<Void, Void, Array<ArticleModel>>() {
        override fun onPostExecute(result: Array<ArticleModel>?) {
            super.onPostExecute(result)
            loadScreenProgressBar.visibility = ProgressBar.GONE
            if (result != null) {
                this@LoadScreenActivity.articles = result
                loadComplete()
            } else {
                reloadLoadScreenButton.visibility = Button.VISIBLE
                loadScreenErrorMsg.visibility = TextView.VISIBLE
            }
        }

        override fun onPreExecute() {
            val asyncObj = this
            Handler().postDelayed({
                if (asyncObj.status != Status.FINISHED) asyncObj.cancel(true)
            }, 10000)
            loadScreenProgressBar.visibility = ProgressBar.VISIBLE
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
//        set Logo
        Glide
            .with(this)
            .load(R.mipmap.logo)
            .into(loadScreenImage)

        reloadLoadScreenButton.setOnClickListener {
            reloadActivity()
        }
    }

}
