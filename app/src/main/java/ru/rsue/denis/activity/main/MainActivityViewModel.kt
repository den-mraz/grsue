package ru.rsue.denis.activity.main
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rsue.app.view.activity.MainActivityState
import ru.rsue.denis.model.main.MainItemModel
import ru.rsue.denis.state.ScreenState
class MainActivityViewModel(private val mainActivityInteractor: MainActivityInteractor) : ViewModel() {
    private lateinit var _navItemsState: MutableLiveData<ScreenState<MainActivityState>>
    val navItemsState: LiveData<ScreenState<MainActivityState>>
        get() {
            if (!::_navItemsState.isInitialized) {
                _navItemsState = MutableLiveData()
                mainActivityInteractor.getNavItems(::onNavItemsLoaded)
            }
            return _navItemsState
        }
    private fun onNavItemsLoaded(navItems: ArrayList<MainItemModel>) {
        _navItemsState.value = ScreenState.Render(MainActivityState.ShowNavItems(navItems))
    }
    fun onNavItemClicked(navItem: MainItemModel) {
        _navItemsState.value = ScreenState.Render(MainActivityState.HandleNavItemClick(navItem))
    }
    class MainActivityViewModelFactory(private val mainActivityInteractor: MainActivityInteractor) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(mainActivityInteractor) as T
        }
    }

}