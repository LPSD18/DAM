package dam.a48168.coolweatherapp

import android.content.pm.PackageManager
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.CacheRequest
import java.net.URL

class MainActivity : AppCompatActivity() {
    private var day: Boolean = false
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {

        val currentime = getCurrentHour()
        day = currentime in 6..18
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT ->
                if (day) setTheme(R.style.Theme_Day)
                else {
                    setTheme(R.style.Theme_Night)


                }

            Configuration.ORIENTATION_LANDSCAPE ->
                if (day) setTheme(R.style.Theme_Day_Land)
                else setTheme(R.style.Theme_Night_Land)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getCurrentLocation()
        configureButton()
        configureCurrentButton()
    }

    private fun getCurrentHour(): Int {
        val currentTimeMillis = System.currentTimeMillis()
        val currentHour = (currentTimeMillis / (1000 * 60 * 60)) % 24
        return currentHour.toInt()
    }



    private fun WeatherAPI_Call(lat: Float, long: Float): WeatherData {

        val reqString = buildString {
            append("https://api.open-meteo.com/v1/forecast?")
            append("latitude=${lat}&longitude=${long}&")
            append("current_weather=true&")
            append("hourly=temperature_2m,weathercode,pressure_msl,windspeed_10m&")
            append("daily=sunrise,sunset")
        }
        val url = URL(reqString)
        url.openStream().use {
            return Gson().fromJson(InputStreamReader(it, "UTF-8"), WeatherData::class.java)
        }
    }

    

    private fun fetchWeatherData(lat: Float, long: Float): Thread {
        return Thread {
            val weather = WeatherAPI_Call(lat, long)
            updateUI(weather)
        }
    }



    private fun updateUI(request: WeatherData) {
        runOnUiThread {
            val weatherImage: ImageView = findViewById(R.id.imageView)
            val pressure: TextView = findViewById(R.id.seaLevelValue)
            val windDir: TextView = findViewById(R.id.windDirValue)
            val windSpeed: TextView = findViewById(R.id.windSpeedValue)
            val temperature: TextView = findViewById(R.id.temperatureValue)
            val time: TextView = findViewById(R.id.timeValue)
            val tomorrow : TextView = findViewById(R.id.YesterdayChange)
            val TwoDays : TextView = findViewById(R.id.TwoDaysChange)
            val ThreeDays : TextView = findViewById(R.id.ThreeDaysChange)
            val FourDays : TextView = findViewById(R.id.FourDaysChange)
            val FiveDays : TextView = findViewById(R.id.FiveDaysChange)
            val SixDays : TextView = findViewById(R.id.SixDaysChange)
            pressure.text = request.hourly.pressure_msl.get(12).toString() + " hPa"
            windDir.text = request.current_weather.winddirection.toString() + "º"
            windSpeed.text = request.current_weather.windspeed.toString() + "km/h"
            temperature.text = request.current_weather.temperature.toString() + "ºC"
            time.text = request.current_weather.time
            tomorrow.text = request.hourly.temperature_2m.get(24).toString() + "ºC"
            TwoDays.text = request.hourly.temperature_2m.get(48).toString() + "ºC"
            ThreeDays.text = request.hourly.temperature_2m.get(72).toString() + "ºC"
            FourDays.text = request.hourly.temperature_2m.get(96).toString() + "ºC"
            FiveDays.text = request.hourly.temperature_2m.get(120).toString() + "ºC"
            SixDays.text = request.hourly.temperature_2m.get(144).toString() + "ºC"
            val mapt = request.getWeatherCodeMap()
            val wcode = mapt?.get(request.current_weather.weathercode)
            val wImage = when (wcode) {
                WeatherData.WMO_WeatherCode.CLEAR_SKY,
                WeatherData.WMO_WeatherCode.MAINLY_CLEAR,
                WeatherData.WMO_WeatherCode.PARTLY_CLOUDY -> if (day) wcode?.image + "day" else wcode?.image + "night"

                else -> wcode?.image
            }
            val res = resources
            weatherImage.setImageResource(R.drawable.fog)
            val resID = res.getIdentifier(wImage, "drawable", packageName)
            val drawable = this.getDrawable(resID)
            weatherImage.setImageDrawable(drawable)


        }
    }

    private fun configureButton() {
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener(View.OnClickListener {
            val latitude: EditText = findViewById(R.id.editLatitude)
            val longitude: EditText = findViewById((R.id.editLongitude))
            val latitudeText: String = latitude.text.toString()
            val latitudeValue: Float = latitudeText.toFloat()
            val longitudeText: String = longitude.text.toString()
            val longitudeValue: Float = longitudeText.toFloat()
            fetchWeatherData(latitudeValue, longitudeValue).start()
        })
    }

    private fun configureCurrentButton() {
        val buttonGet = findViewById<Button>(R.id.getCurrentLocation)
        buttonGet.setOnClickListener {
            getCurrentLocation()
        }
    }

    private fun getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )

        }
        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if (it != null) {
                val textLatitude = it.latitude
                val textLongitude = it.longitude
                val editLatitude = findViewById<EditText>(R.id.editLatitude)
                val editLongitude = findViewById<EditText>(R.id.editLongitude)
                editLatitude.text = Editable.Factory.getInstance().newEditable(textLatitude.toString())
                editLongitude.text = Editable.Factory.getInstance().newEditable(textLongitude.toString())
                fetchWeatherData(textLatitude.toFloat(),textLongitude.toFloat()).start()

            }
        }

    }

}
