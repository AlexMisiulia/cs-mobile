package com.binarysages.mobile.app.corespirit.activity.loadScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.itemId
import com.binarysages.mobile.app.corespirit.activity.mainActivity.MainActivity
import com.binarysages.mobile.app.corespirit.models.ArticleTree
import com.binarysages.mobile.app.corespirit.models.ArticlesModel
import com.binarysages.mobile.app.corespirit.network.NetworkService
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_load_screen_activty.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadScreenActivity : AppCompatActivity() {
    private fun reloadActivity() {
        finish()
        startActivity(intent)
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

        val intent = Intent(this, MainActivity::class.java)
        NetworkService
            .getInstance(false)
            .getCategoriesApi()
            .getCategoriesTree()
            .enqueue(object : Callback<ArticleTree> {
                override fun onFailure(call: Call<ArticleTree>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<ArticleTree>, response: Response<ArticleTree>) {
                    val bundle = Bundle()
                    bundle.putSerializable("menuTree", response.body())
                    intent.putExtra("menuTreeBundle", bundle)
                }
            })

        NetworkService
            .getInstance()
            .getJsonApi()
            .getArticles(itemId)
            .enqueue(object : Callback<ArticlesModel> {
                override fun onFailure(call: Call<ArticlesModel>, t: Throwable) {
                    TODO("NOT IMPL")
                }

                override fun onResponse(
                    call: Call<ArticlesModel>,
                    response: Response<ArticlesModel>
                ) {
                    response.body()?.data?.articles.let {
                        val bundle = Bundle()
                        bundle.putSerializable("articles", it)
                        intent.putExtra("BUNDLE", bundle)
                        startActivity(intent)
                    }
                }
            })
    }
}
