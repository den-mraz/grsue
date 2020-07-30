package ru.rsue.denis.activity.main

import ru.rsue.denis.R
import ru.rsue.denis.model.main.MainItemModel

class MainActivityInteractor {

    fun getNavItems(callback: (ArrayList<MainItemModel>) -> Unit) {
        callback(createNavItemList())
    }

    private fun createNavItemList(): ArrayList<MainItemModel> {

        val navItems:ArrayList<MainItemModel>? = ArrayList()

        navItems?.add(MainItemModel(1, R.drawable.ic_chat_bubble_black_24dp,"Новости"))
        navItems?.add(MainItemModel(2,R.drawable.ic_font_download_black_24dp,"Расписание"))
        navItems?.add(MainItemModel(3,R.drawable.ic_work_black_24dp,"Преподователи"))
        navItems?.add(MainItemModel(4,R.drawable.ic_people_black_24dp,"Студ. жизнь"))
        return navItems!!
    }

}