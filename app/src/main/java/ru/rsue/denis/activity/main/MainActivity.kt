package ru.rsue.denis.activity.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_stud_life_category.*
import kotlinx.android.synthetic.main.nav_drawer_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rsue.app.view.activity.MainActivityState
import ru.rsue.denis.R
import ru.rsue.denis.adapter.main.NavAdapter
import ru.rsue.denis.api.main.MainApiInterface
import ru.rsue.denis.model.main.MainItemModel
import ru.rsue.denis.state.ScreenState
import ru.rsue.denis.state.enableAnimatedBackground
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(
            this,
            MainActivityViewModel.MainActivityViewModelFactory(MainActivityInteractor())
        )[MainActivityViewModel::class.java]
        viewModel.navItemsState.observe(::getLifecycle, ::updateDrawerItems)
        setDrawer()

        val err = Toast.makeText(applicationContext, R.string.erconn, Toast.LENGTH_SHORT)
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.10:8099/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(MainApiInterface::class.java)
            api.fetchMain().enqueue(object : Callback<List<MainItemModel>> {
                override fun onFailure(call: Call<List<MainItemModel>>, t: Throwable) {
                    err.show()
                    Log.d("-er-", "-${t}")
                }
                override fun onResponse(
                    call: Call<List<MainItemModel>>,
                    response: Response<List<MainItemModel>>
                ) {
                    showData(response.body()!!)
                }
            })
        }catch (e: IOException){}
    }
    private fun showData(main: List<MainItemModel>) {
        studLifeCategoryRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = NavAdapter(main)
        }
    }

    private fun setDrawer() {
        toggle = object : ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.nav_drawer_open, R.string.nav_drawer_close){
            val scaleFactor = 6f
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val slideX = drawerView.width.times(slideOffset)
                content.apply {
                    translationX = slideX
                    scaleX = 1.minus(slideOffset.div(scaleFactor))
                    scaleY = 1.minus(slideOffset.div(scaleFactor))
                }
            }
        }.apply { syncState() }

        drawerLayout.apply {
            setScrimColor(Color.TRANSPARENT)
            drawerElevation = 0f
            addDrawerListener(toggle)
            enableAnimatedBackground()
        }
    }

    private fun updateDrawerItems(screenState: ScreenState<MainActivityState>?) {
        when (screenState) {
            is ScreenState.Render -> setDrawerItems(screenState.renderState)
        }
    }

    private fun setDrawerItems(renderState: MainActivityState) {
       // val intentToTeacher= Intent(this@MainActivity, TeacherActivity::class.java)
       // val intentToStudLive=Intent(this@MainActivity, StudLifeCategoryActivity::class.java)
      //val intentToNews=Intent(this@MainActivity, NewsActivity::class.java)
        //val intentToSchedule=Intent(this@MainActivity, ScheduleActivity::class.java)
 try {

        when (renderState) {
            is MainActivityState.ShowNavItems -> {
                navRecycler.apply {
                    layoutManager = GridLayoutManager(this@MainActivity, 1)
                    adapter = NavAdapter(renderState.navItems, viewModel::onNavItemClicked)
                }
            }
                is MainActivityState.HandleNavItemClick -> {
                drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
        }
        catch (e:IOException){Log.d("-er-", "$e")}
    }



}
/*




}


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }





}

        */

