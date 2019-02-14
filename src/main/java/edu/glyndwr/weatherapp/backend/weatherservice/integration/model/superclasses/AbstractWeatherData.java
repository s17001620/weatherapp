

package edu.glyndwr.weatherapp.backend.weatherservice.integration.model.superclasses;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
import edu.glyndwr.weatherapp.backend.weatherservice.integration.model.WeatherData;
import java.util.Calendar;
import org.json.JSONObject;

abstract public class AbstractWeatherData {
        public static final String CITY_NOT_FOUND = "CITY NOT FOUND!";
        public static final String NOT_AVAILABLE = "NOT AVAILABLE!";
        public static final String WEATHERDATA_IMAGE_PATH = "http://openweathermap.org/img/w/";
        public static final String WEATHERDATA_IMAGE_FILETYPE = ".png";
        public static final String WEATHERDATA_DATE_FORMAT = "EEEE, dd/MM/yyyy HH Z";
    
        public static final String PRESSURE_UNIT="hpa";
        public static final String HUMIDITY_UNIT="%";
        public static final String WINDSPEED_UNIT="m/s";
        public static final String TEMPERATURE_KELVIN_UNIT= "°k";
        public static final String TEMPERATURE_CELSIUS_UNIT= "°C";
        
        protected static final String JSON_DATE_TIME = "dt";
        protected static final String JSON_WEATHER   = "weather";
	protected static final String JSON_MAIN      = "main";
	protected static final String JSON_WIND      = "wind";

	static abstract public class Main {
		public static final String JSON_TEMP     = "temp";
		public static final String JSON_TEMP_MIN = "temp_min";
		public static final String JSON_TEMP_MAX = "temp_max";
		public static final String JSON_HUMIDITY = "humidity";
		public static final String JSON_PRESSURE = "pressure";

		abstract public float getTemp ();
		abstract public float getTempMin ();
		abstract public float getTempMax ();
		abstract public float getPressure ();
		abstract public float getHumidity ();
	}

	static abstract public class Wind {
		public static final String JSON_SPEED   = "speed";
		public static final String JSON_DEG     = "deg";
		public static final String JSON_GUST    = "gust";
		public static final String JSON_VAR_BEG = "var_beg";
		public static final String JSON_VAR_END = "var_end";

		abstract public float getSpeed ();
		abstract public int getDeg ();
		abstract public float getGust ();
		abstract public int getVarBeg ();
		abstract public int getVarEnd ();
	}
        
        static abstract public class Weather {
            public static final String JSON_VAR_MAIN_ID = "id";
            public static final String JSON_VAR_MAIN_ICON = "icon";
            public static final String JSON_VAR_MAIN_WEATHER = "main";
            public static final String JSON_VAR_MAIN_WEATHER_DESCRIPTION = "description";
            
            abstract public float getMainWeather ();
            abstract public float getDescription ();
        }
        

	protected final long dateTime;

        public AbstractWeatherData () {
		this.dateTime = Calendar.getInstance().getTimeInMillis();
	}
        
	public AbstractWeatherData (JSONObject json) {
		this.dateTime = json.optLong (WeatherData.JSON_DATE_TIME, Long.MIN_VALUE);
	}

	public long getDateTime () {
		return this.dateTime;
	}


}