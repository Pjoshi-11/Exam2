package com.example.exam2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerCity: Spinner
    private lateinit var btnFetchWeather: Button
    private lateinit var tvWeatherInfo: TextView

    private val cities = arrayOf("New York", "Los Angeles", "Chicago", "Houston", "Phoenix")
    private val weatherData = mapOf(
        "New York" to "Sunny, 25°C",
        "Los Angeles" to "Cloudy, 20°C",
        "Chicago" to "Rainy, 18°C",
        "Houston" to "Sunny, 30°C",
        "Phoenix" to "Hot, 35°C"
    )

    private var selectedCity: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerCity = findViewById(R.id.spinnerCity)
        btnFetchWeather = findViewById(R.id.btnFetchWeather)
        tvWeatherInfo = findViewById(R.id.tvWeatherInfo)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCity.adapter = adapter

        spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCity = cities[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedCity = null
            }
        }

        btnFetchWeather.setOnClickListener {
            if (selectedCity != null) {
                val weatherInfo = weatherData[selectedCity]
                tvWeatherInfo.text = "Weather in $selectedCity: $weatherInfo"
            } else {
                Toast.makeText(this, "Please select a city", Toast.LENGTH_SHORT).show()
            }
        }
    }
}