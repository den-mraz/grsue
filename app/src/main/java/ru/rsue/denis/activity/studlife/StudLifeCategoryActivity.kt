package ru.rsue.denis.activity.studlife

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_stud_life_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rsue.denis.R
import ru.rsue.denis.adapter.studlife.StudLifeCategoryAdapter
import ru.rsue.denis.api.studlife.StudLifeCategoryApiInterface
import ru.rsue.denis.model.studlife.GetStudLifeCategory
import java.io.IOException


class StudLifeCategoryActivity: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_life_category)
        setSupportActionBar(toolbar)

        val err = Toast.makeText(applicationContext, R.string.erconn, Toast.LENGTH_SHORT)
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.10:8099/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(StudLifeCategoryApiInterface::class.java)
            api.fetchStudLifeCategory().enqueue(object : Callback<List<GetStudLifeCategory>> {
                override fun onFailure(call: Call<List<GetStudLifeCategory>>, t: Throwable) {
                    err.show()
                    Log.d("-er-", "-${t}")
                }
                override fun onResponse(
                    call: Call<List<GetStudLifeCategory>>,
                    response: Response<List<GetStudLifeCategory>>
                ) {

                    showData(response.body()!!)
                }
            })
        }catch (e: IOException){}
    }


    private fun showData(category: List<GetStudLifeCategory>) {
       studLifeCategoryRv.apply {
            layoutManager = LinearLayoutManager(this@StudLifeCategoryActivity)
            adapter = StudLifeCategoryAdapter(category, {categorys -> personItemClicked(categorys)})
        }
    }

    private fun personItemClicked(categorys: GetStudLifeCategory) {
        val intentToStudLive = Intent(this@StudLifeCategoryActivity, StudLifeActivity::class.java)
        //Toast.makeText(this, "Clicked: ${categorys.name_studLifeCategory}", Toast.LENGTH_SHORT).show()
        startActivity(intentToStudLive)
    }

}
