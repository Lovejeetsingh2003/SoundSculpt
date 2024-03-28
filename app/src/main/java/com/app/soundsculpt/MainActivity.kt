package com.app.soundsculpt

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.app.soundsculpt.adapter.MusicAdapter
import com.app.soundsculpt.databinding.ActivityMainBinding
import com.app.soundsculpt.entity.MusicEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    var binding : ActivityMainBinding ?= null
    var myAdapter  : MusicAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MusicInterface::class.java)
        val retrofitData = retrofitBuilder.getData("arjan dhillon")
        
        retrofitData.enqueue(object : Callback<MusicEntity?> {
            override fun onResponse(call: Call<MusicEntity?>, response: Response<MusicEntity?>) {
              val data = response.body()?.data!!
               myAdapter = MusicAdapter(this@MainActivity,data)
                binding?.rvMusic?.adapter = myAdapter
                binding?.rvMusic?.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<MusicEntity?>, t: Throwable) {
                Log.d("OnFailure :", "onFailure: " + t.message)
            }
        })
    }
}