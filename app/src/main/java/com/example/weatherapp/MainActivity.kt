package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var latitude:Double = 28.6219
    var longitude:Double = 77.0878
    private lateinit var apiKey: String
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var WeatherList: futureWeather

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiKey = getString(R.string.API_KEY)
        getCurrentWeather()

        GlobalScope.launch {
            val future = withContext(Dispatchers.IO) { Client.api.getFutureWeather(latitude,longitude,apiKey) }
            Log.d("FutureResponse", future.body().toString())
            withContext(Dispatchers.Main){
                if(future.isSuccessful){
                    val futureList = future.body()?.list
                    val adapter = WeatherAdapter(futureList as List<ListItem>)

                    rvList.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
                    rvList.adapter = adapter
                }
            }

        }
    }

    private fun getCurrentWeather() {
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ Client.api.getCurrentWeather(latitude,longitude,apiKey) }
            Log.d("WeatherResponse", response.body().toString())
            if(response.isSuccessful){
                withContext(Dispatchers.Main){
                    val weatherList = response.body()?.list
                    Log.d("WeatherList", weatherList.toString())
                    tvLocation.text = response.body()?.city?.name.toString()
                    if (weatherList != null) {
//                        updateTv.text = "Updated at ${weatherList[0]?.dt_txt}"
                        updateTv.text = formatUpdateTime(weatherList[0]?.dt_txt)
//                        val temperature = weatherList[0]?.main?.temp.toString()
                        val temperature = String.format("%.0f", weatherList[0]?.main?.temp)
                        temp.text = temperature
                        val desc = weatherList[0]?.weather?.getOrNull(0)?.description
                        tvDesc.text = desc?.capitalize()
                        val feelsLike = String.format("%.0f",weatherList[0]?.main?.feels_like)
                        tvFeels.text = "${feelsLike}°C"
                        val tempMax = String.format("%.0f",weatherList[0]?.main?.temp_max)
                        val tempMin = String.format("%.0f",weatherList[0]?.main?.temp_min)
                        tvMin.text = "${tempMin}°C"
                        tvMax.text = "${tempMax}°C"
                        val windSp = String.format("%.0f",weatherList[0]?.wind?.speed)
                        pressureTv.text = "${weatherList[0]?.main?.pressure} hPa"
                        windTv.text = "${windSp}m/s"
                        humidityTv.text = "${weatherList[0]?.main?.humidity}%"
                    }

                }
            }
        }
    }

    fun formatUpdateTime(dateTime: String?): String {
        if (dateTime.isNullOrBlank()) {
            return "N/A"
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val updateTime = dateFormat.parse(dateTime)?.time ?: 0
        val currentTime = System.currentTimeMillis()

        val timeDifference = currentTime - updateTime
        val hoursDifference = kotlin.math.abs(timeDifference / (60 * 60 * 1000))

        return "Updated $hoursDifference hours ago"
    }
}