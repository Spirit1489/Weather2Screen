package ru.spiritblog.weather2screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import java.util.*

private const val TAG = "WeatherFragment"
private const val ARG_CRIME_ID = "crime_id"


class WeatherFragment : Fragment() {


    private lateinit var weather: Weather
    private lateinit var titleField: TextView
    private val weatherDetailViewModel: WeatherDetailViewModel by lazy {
        ViewModelProviders.of(this).get(WeatherDetailViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weather = Weather()
        val weatherId: UUID = arguments?.getSerializable(ARG_CRIME_ID) as UUID

        weatherDetailViewModel.loadWeather(weatherId)


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        titleField = view.findViewById(R.id.cityName)



        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherDetailViewModel.weatherLiveData.observe(
            viewLifecycleOwner, androidx.lifecycle.Observer { weather ->
                weather?.let {
                    this.weather = weather
                    updateUI()
                }


            }

        )


    }


    override fun onStop() {
        super.onStop()
        weatherDetailViewModel.saveWeather(weather)
    }

    private fun updateUI() {
        titleField.setText(weather.cityTitle)


    }


    companion object {

        fun newInstance(weatherId: UUID): WeatherFragment {
            val args = Bundle().apply {
                putSerializable(ARG_CRIME_ID, weatherId)
            }

            return WeatherFragment().apply {
                arguments = args
            }


        }


    }


}