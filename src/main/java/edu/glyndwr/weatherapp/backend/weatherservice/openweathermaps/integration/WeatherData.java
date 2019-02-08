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

@Getter
@Setter
public class WeatherData extends AbstractWeatherData implements Serializable {

    private float temp;
    private float tempMin;
    private float tempMax;
    private float pressure;
    private float humidity;
    private float speed;
    private int deg;
    private float gust;
    private int varBeg;
    private int varEnd;

    private Instant timestamp;
    private Integer weatherId;
    private String weatherIcon;

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
        this.speed = (Float.parseFloat(wind.get(JSON_SPEED).toString()));
        this.deg = (Integer.parseInt(wind.get(JSON_DEG).toString()));
        this.gust = (Float.parseFloat(wind.get(JSON_GUST).toString()));
        this.varBeg = (Integer.parseInt(wind.get(JSON_VAR_BEG).toString()));
        this.varEnd = (Integer.parseInt(wind.get(JSON_VAR_END).toString()));
    }

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        setWeatherId((Integer) weather.get("id"));
        setWeatherIcon((String) weather.get("icon"));
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
        return !Float.isNaN(this.speed);
    }

    public boolean hasDeg() {
        return this.deg != Integer.MIN_VALUE;
    }

    public boolean hasGust() {
        return !Float.isNaN(this.gust);
    }

    public boolean hasVarBeg() {
        return this.varBeg != Integer.MIN_VALUE;
    }

    public boolean hasVarEnd() {
        return this.varEnd != Integer.MIN_VALUE;
    }

}
