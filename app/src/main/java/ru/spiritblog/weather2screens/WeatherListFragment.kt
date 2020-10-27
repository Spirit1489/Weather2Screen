package ru.spiritblog.weather2screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TabHost
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.spiritblog.weather2screens.api.WeatherApi
import ru.spiritblog.weather2screens.api.WeatherFetchr
import java.util.*

private const val TAG = "WeatherListFragment"


class WeatherListFragment : Fragment() {

    /**
     * Required interface for hosting activities
     */

    interface Callbacks {
        fun onWeatherSelected(weatherId: UUID)
    }


    private var callbacks: Callbacks? = null


    private lateinit var weatherRecyclerView: RecyclerView
    private var adapter: WeatherAdapter? = WeatherAdapter(emptyList())


    private val weatherListViewModel: WeatherListViewModel by lazy {
        ViewModelProviders.of(this).get(WeatherListViewModel::class.java)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        callbacks = context as Callbacks?

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val weatherLiveData: LiveData<List<Weather>> = WeatherFetchr().fetchWeathers()

        weatherLiveData.observe(this, Observer { weathers ->
            Log.d(TAG, "Response received: $weathers")

        })


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_weather_list, container, false)
        weatherRecyclerView = view.findViewById(R.id.weather_recycler_view) as RecyclerView
        weatherRecyclerView.layoutManager = LinearLayoutManager(context)
        weatherRecyclerView.adapter = adapter


        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherListViewModel.weatherListLiveData.observe(
            viewLifecycleOwner, Observer { weathers ->
                weathers?.let {
                    Log.i(TAG, "Got weathers ${weathers.size}")
                    updateUI(weathers)
                }
            }
        )


    }


    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_weather_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_weather -> {
                val weather = Weather()
                weatherListViewModel.addWeather(weather)
                callbacks?.onWeatherSelected(weather.id)
                true
            }
            else -> return super.onOptionsItemSelected(item)

        }


    }

    private fun updateUI(weathers: List<Weather>) {
        adapter = WeatherAdapter(weathers)
        weatherRecyclerView.adapter = adapter


    }


    private inner class WeatherHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private lateinit var weather: Weather


        private val titleTextView: TextView = itemView.findViewById(R.id.weather_city)
        private val temperatureTextView: TextView = itemView.findViewById(R.id.weather_temperature)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.weather_description)


        init {
            itemView.setOnClickListener(this)
        }


        fun bind(weather: Weather) {
            this.weather = weather
            titleTextView.text = this.weather.cityTitle
            temperatureTextView.text = this.weather.temperature.toString()
            descriptionTextView.text = this.weather.description


        }


        override fun onClick(v: View) {
            callbacks?.onWeatherSelected(weather.id)
        }
    }


    private inner class WeatherAdapter(var weathers: List<Weather>) :
        RecyclerView.Adapter<WeatherHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
            val view = layoutInflater.inflate(R.layout.list_item_weather, parent, false)
            return WeatherHolder(view)
        }

        override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
            val weather = weathers[position]
            holder.bind(weather)
        }

        override fun getItemCount(): Int {
            return weathers.size
        }
    }


    companion object {
        fun newInstance(): WeatherListFragment {


            return WeatherListFragment()


        }

    }


}