package ru.rsue.denis.activity.studlife

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.rsue.denis.R

import kotlinx.android.synthetic.main.activity_stud_life.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_stud_life.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rsue.denis.adapter.studlife.StudLifeAdapter
import ru.rsue.denis.api.studlife.StudLifeApiInterface
import ru.rsue.denis.model.studlife.GetStudLife
import java.io.IOException

class StudLifeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_life)
        setSupportActionBar(toolbar)

        val err = Toast.makeText(applicationContext, R.string.erconn, Toast.LENGTH_SHORT)
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.10:8099/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(StudLifeApiInterface::class.java)
            api.fetchStudLife().enqueue(object : Callback<List<GetStudLife>> {
                override fun onFailure(call: Call<List<GetStudLife>>, t: Throwable) {
                    err.show()
                    Log.d("-er-", "-${t}")
                }
                override fun onResponse(
                    call: Call<List<GetStudLife>>,
                    response: Response<List<GetStudLife>>
                ) {
                    showData(response.body()!!)
                }
            })
        }catch (e: IOException){}
    }


    private fun showData(studlife: List<GetStudLife>) {
        studLifeRv.apply {
            layoutManager = LinearLayoutManager(this@StudLifeActivity)
            adapter = StudLifeAdapter(studlife)
        }
    }

}