package com.binarysages.mobile.app.corespirit.menus

import android.view.Menu
import android.view.SubMenu
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.ArticleTree

private fun getSubMenu(subMenu: SubMenu?, articleTree: Array<ArticleTree.Data.CategoryTree>) {
    for (obj in articleTree) {
        if (obj.categories?.isNotEmpty()!! && obj.hasArticles) {
            val subMenInu: SubMenu? = subMenu?.addSubMenu(obj.name)
            getSubMenu(subMenInu, obj.categories)
        } else {
            if (obj.hasArticles) {
                subMenu?.add(0, obj.id.toInt(), 0, obj.name)
            }
        }
    }
}

fun generateMenuFromTree(menu: Menu?, menuTree: ArticleTree) {
    for (obj in menuTree.data.categoryTree) {
        if (obj.categories?.isNotEmpty()!!) {
            val subMenu: SubMenu? =
                menu?.findItem(R.id.articleListMenu)?.subMenu?.addSubMenu(obj.name)
            getSubMenu(subMenu, obj.categories)
        } else {
            menu?.findItem(R.id.articleListMenu)?.subMenu?.add(obj.name)
        }
    }
}