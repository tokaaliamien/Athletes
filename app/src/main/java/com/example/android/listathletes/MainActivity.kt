package com.example.android.listathletes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))

        getAthletes()
    }

    private fun getAthletes() {
        val url = URL("https://gist.githubusercontent.com/MohamedWael/1406437f14e9a769a3a572a78906388f/raw/5be50e67c96c5ed1da9fe6344d2dd7befef0ba25/")
        val request = Request.Builder().url(url!!).build()

        progress_bar.visibility = View.VISIBLE;

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                this@MainActivity.runOnUiThread(java.lang.Runnable {
                    Toast.makeText(this@MainActivity, getString(R.string.errorMessage), Toast.LENGTH_LONG).show()
                    progress_bar.visibility = View.INVISIBLE;
                })
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                val jsonParser = JsonParser()

                val jsonObject = jsonParser.parse(body) as JsonObject
                val jsonArray = jsonObject.getAsJsonArray("athletes")
                val gson = Gson()

                val arrayListType = object : TypeToken<ArrayList<Athlete>>() {}.type
                val athletes: ArrayList<Athlete> = gson.fromJson(jsonArray, arrayListType)

                this@MainActivity.runOnUiThread(java.lang.Runnable {
                    progress_bar.visibility = View.INVISIBLE;
                    recyclerView.adapter = AthletesAdapter(athletes, this@MainActivity)
                })

            }
        })

    }

}


