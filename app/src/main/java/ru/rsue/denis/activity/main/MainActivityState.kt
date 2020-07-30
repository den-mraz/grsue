package ru.rsue.app.view.activity

import ru.rsue.denis.model.main.MainItemModel


sealed class MainActivityState {
    class ShowNavItems(val navItems: ArrayList<MainItemModel>) : MainActivityState()
    class HandleNavItemClick(val navItem: MainItemModel) : MainActivityState()
}