package com.example.weatherapp

data class Weather(
	val city: City? = null,
	val cnt: Int? = null,
	val cod: String? = null,
	val message: Int? = null,
	val list: List<ListItem?>? = null
)

data class futureWeather(
	val list: List<ListItem?>? = null
)

data class Main(
	val temp: Any? = null,
	val temp_min: Any? = null,
	val grnd_level: Int? = null,
	val tempKf: Any? = null,
	val humidity: Int? = null,
	val pressure: Int? = null,
	val sea_level: Int? = null,
	val feels_like: Any? = null,
	val temp_max: Any? = null
)

data class Coord(
	val lon: Any? = null,
	val lat: Any? = null
)

data class Sys(
	val pod: String? = null
)

data class Wind(
	val deg: Int? = null,
	val speed: Any? = null,
	val gust: Any? = null
)

data class WeatherItem(
	val icon: String? = null,
	val description: String? = null,
	val main: String? = null,
	val id: Int? = null
)

data class City(
	val country: String? = null,
	val coord: Coord? = null,
	val sunrise: Int? = null,
	val timezone: Int? = null,
	val sunset: Int? = null,
	val name: String? = null,
	val id: Int? = null,
	val population: Int? = null
)

data class Clouds(
	val all: Int? = null
)

data class ListItem(
	val dt: Int? = null,
	val pop: Int? = null,
	val visibility: Int? = null,
	val dt_txt: String? = null,
	val weather: List<WeatherItem?>? = null,
	val main: Main? = null,
	val clouds: Clouds? = null,
	val sys: Sys? = null,
	val wind: Wind? = null
)

