package com.example.dai_01.fleavatest.model

import java.io.Serializable


data class WeatherListResponse(
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: List<WeatherList>,
    val city: City
): Serializable

data class WeatherList(
    val dt: Int,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val sys: Sys,
    val dt_txt: String
): Serializable

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
): Serializable

data class Main(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val sea_level: Double,
    val grnd_level: Double,
    val humidity: Int
): Serializable

data class Sys(
    val pod: String
): Serializable

data class Clouds(
    val all: Int
): Serializable

data class Wind(
    val speed: Double,
    val deg: Double
): Serializable

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int
): Serializable

data class Coord(
    val lat: Double,
    val lon: Double
): Serializable