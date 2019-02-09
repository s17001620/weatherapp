package edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import static edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.AbstractWeatherData.Main.JSON_HUMIDITY;
import static edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.AbstractWeatherData.Main.JSON_PRESSURE;
import static edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.AbstractWeatherData.Main.JSON_TEMP;
import static edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.AbstractWeatherData.Main.JSON_TEMP_MAX;
import static edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.AbstractWeatherData.Main.JSON_TEMP_MIN;
import static edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.AbstractWeatherData.Wind.JSON_DEG;
import static edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.AbstractWeatherData.Wind.JSON_GUST;
import static edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.AbstractWeatherData.Wind.JSON_SPEED;
import static edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.AbstractWeatherData.Wind.JSON_VAR_BEG;
import static edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.AbstractWeatherData.Wind.JSON_VAR_END;
import lombok.Setter;
import lombok.Getter;
import org.json.JSONObject;

@Getter
@Setter
public class WeatherData extends AbstractWeatherData implements Serializable {

    private float temp;
    private float tempMin;
    private float tempMax;
    private float pressure;
    private float humidity;
    private Double speed;
    private Double deg;
    private Double gust;
    private Integer varBeg;
    private Integer varEnd;
    private String mainWeather;
    private String mainWeatherDescription;

    private Instant timestamp;
    private Integer weatherId;
    private String weatherIcon;
    
       	public WeatherData () {
		super();
	}

    	public WeatherData (JSONObject json) {
		super(json);
	}

    
    
    @JsonProperty("timestamp")
    public Instant getTimestamp() {
        return this.timestamp;
    }

    @JsonSetter("dt")
    public void setTimestamp(long unixTime) {
        this.timestamp = Instant.ofEpochMilli(unixTime * 1000);
    }

    @JsonProperty("main")
    public void setMain(Map<String, Object> main) {
        setTemp(Float.parseFloat(main.get(JSON_TEMP).toString()));
        setTempMin(Float.parseFloat(main.get(JSON_TEMP_MIN).toString()));
        setTempMax(Float.parseFloat(main.get(JSON_TEMP_MAX).toString()));
        setPressure(Float.parseFloat(main.get(JSON_PRESSURE).toString()));
        setHumidity(Float.parseFloat(main.get(JSON_HUMIDITY).toString()));
    }

   
    
    @JsonProperty("wind")
    public void setWind(Map<String, Object> wind) {

        final Object windSpeedRaw = wind.get(JSON_SPEED);
        final Object windDegRaw = wind.get(JSON_DEG);
        final Object windGustRaw = wind.get(JSON_GUST);
        final Object windVarBegRaw = wind.get(JSON_VAR_BEG);
        final Object windVarEnd = wind.get(JSON_VAR_END);

        if (null != windSpeedRaw) {
            if(windSpeedRaw instanceof Double){
            this.speed =  (Double) windSpeedRaw;
            }
        }else{
               this.speed = 0.0;
            }
        if (null != windDegRaw) {
            if (windDegRaw instanceof Double) {
                this.deg = (Double) windDegRaw;
            }
        }else{
                this.deg = 0.0;
            }
        if (null != windGustRaw) {
            if (windGustRaw instanceof Double) {
                this.gust = (Double) windGustRaw;
            }
        }else{
                this.gust = 0.0;
            }
        if (null != windVarBegRaw) {
            if (windVarBegRaw instanceof Integer) {
                this.varBeg = (Integer) windVarBegRaw;
            }
        }else{
                this.varBeg = 0;
            }
        if (null != windVarEnd) {
            if (windVarEnd instanceof Integer) {
                this.varEnd = (Integer) windVarBegRaw;
            }
        }else{
                 this.varEnd = 0;
            }
    }

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        setWeatherId((Integer) weather.get(Weather.JSON_VAR_MAIN_ID));
        setWeatherIcon((String) weather.get(Weather.JSON_VAR_MAIN_ICON));
        setMainWeather((String) weather.get(Weather.JSON_VAR_MAIN_WEATHER));
        setMainWeatherDescription((String) weather.get(Weather.JSON_VAR_MAIN_WEATHER_DESCRIPTION));
    }

    public boolean hasTemp() {
        return !Float.isNaN(this.temp);
    }

    public boolean hasTempMin() {
        return !Float.isNaN(this.tempMin);
    }

    public boolean hasTempMax() {
        return !Float.isNaN(this.tempMax);
    }

    public boolean hasPressure() {
        return !Float.isNaN(this.pressure);
    }

    public boolean hasHumidity() {
        return !Float.isNaN(this.humidity);
    }

    public boolean hasSpeed() {
        return !Double.isNaN(this.speed);
    }

    public boolean hasDeg() {
        return this.deg != Integer.MIN_VALUE;
    }

    public boolean hasGust() {
        return !Double.isNaN(this.gust);
    }

    public boolean hasVarBeg() {
        return this.varBeg != Integer.MIN_VALUE;
    }

    public boolean hasVarEnd() {
        return this.varEnd != Integer.MIN_VALUE;
    }

}
