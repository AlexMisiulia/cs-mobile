package com.binarysages.mobile.app.corespirit.menus

import android.view.Menu
import android.view.SubMenu
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.ArticleTree
import com.binarysages.mobile.app.corespirit.network.CORE_SPIRIT_API

private fun getSubMenu(subMenu: SubMenu?, articleTree: Array<ArticleTree.Data.CategoryTree>) {
    for (obj in articleTree) {
        if (obj.categories?.isNotEmpty()!!) {
            val subMenInu: SubMenu? = subMenu?.addSubMenu(obj.name)
            getSubMenu(subMenInu, obj.categories)
        } else {
            subMenu?.add(0, obj.id.toInt(), 0, obj.name)
        }
    }
}

fun generateMenuFromTree(menu: Menu?) {
    val tree = CORE_SPIRIT_API.getTree()
    for (obj in tree.data.categoryTree) {
        if (obj.categories?.isNotEmpty()!!) {
            val subMenu: SubMenu? =
                menu?.findItem(R.id.articleListMenu)?.subMenu?.addSubMenu(obj.name)
            getSubMenu(subMenu, obj.categories)
        } else {
            menu?.findItem(R.id.articleListMenu)?.subMenu?.add(obj.name)
        }
    }
}