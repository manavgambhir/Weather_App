package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_list.view.*

class WeatherAdapter(private val weatherList: List<ListItem>): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun getItemCount(): Int = weatherList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_list,parent,false))
    }


    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    class WeatherViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(listItem: ListItem) = with(itemView){
            descBox.text = listItem.weather?.getOrNull(0)?.description
            tempTv.text = listItem.main?.temp.toString()
            val imgUrl = "https://openweathermap.org/img/wn/${listItem.weather?.getOrNull(0)?.icon}.png"
            Picasso.get().load(imgUrl).into(imageView)
            dateTv.text = listItem.dt_txt
        }

    }

}
