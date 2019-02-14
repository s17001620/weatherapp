package edu.glyndwr.weatherapp.backend.weatherservice.integration.model;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
import edu.glyndwr.weatherapp.backend.weatherservice.integration.model.superclasses.AbstractWeatherData;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Setter;
import lombok.Getter;
import org.json.JSONObject;

@Getter
@Setter
public class WeatherData extends AbstractWeatherData implements Serializable {

    private Double temp;
    private Double tempMin;
    private Double tempMax;
    private Double pressure;
    private Double humidity;
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

    public WeatherData() {
        super();
    }

    public WeatherData(JSONObject json) {
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
        setTemp(parseNumericValueAsDouble(main.get(AbstractWeatherData.Main.JSON_TEMP)));
        setTempMin(parseNumericValueAsDouble(main.get(AbstractWeatherData.Main.JSON_TEMP_MIN)));
        setTempMax(parseNumericValueAsDouble(main.get(AbstractWeatherData.Main.JSON_TEMP_MAX)));
        setPressure(parseNumericValueAsDouble(main.get(AbstractWeatherData.Main.JSON_PRESSURE)));
        setHumidity(parseNumericValueAsDouble(main.get(AbstractWeatherData.Main.JSON_HUMIDITY)));
    }

    @JsonProperty("wind")
    public void setWind(Map<String, Object> wind) {

        final Object windSpeedRaw = wind.get(AbstractWeatherData.Wind.JSON_SPEED);
        final Object windDegRaw = wind.get(AbstractWeatherData.Wind.JSON_DEG);
        final Object windGustRaw = wind.get(AbstractWeatherData.Wind.JSON_GUST);
        final Object windVarBegRaw = wind.get(AbstractWeatherData.Wind.JSON_VAR_BEG);
        final Object windVarEnd = wind.get(AbstractWeatherData.Wind.JSON_VAR_END);

        this.speed = parseNumericValueAsDouble(windSpeedRaw);
        this.deg = parseNumericValueAsDouble(windDegRaw);
        this.gust = parseNumericValueAsDouble(windGustRaw);
        this.varBeg = parseNumericValueAsInteger(windVarBegRaw);
        this.varEnd = parseNumericValueAsInteger(windVarBegRaw);

    }

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        setWeatherId(parseNumericValueAsInteger(weather.get(Weather.JSON_VAR_MAIN_ID)));
        setWeatherIcon((String) weather.get(Weather.JSON_VAR_MAIN_ICON));
        setMainWeather((String) weather.get(Weather.JSON_VAR_MAIN_WEATHER));
        setMainWeatherDescription((String) weather.get(Weather.JSON_VAR_MAIN_WEATHER_DESCRIPTION));
    }

    private Double parseNumericValueAsDouble(Object numVal) {
        Double output = 0.0;
        if (numVal != null) {
            if (numVal instanceof Double) {
                output = (Double) numVal;
            } else if (numVal instanceof Integer) {
                output = Double.valueOf((Integer) numVal);
            } else if (numVal instanceof String) {
                output = Double.valueOf((String) numVal);
            }
        }
        return output;
    }

    private Integer parseNumericValueAsInteger(Object numVal) {
        Integer output = 0;
        if (numVal != null) {
            if (numVal instanceof Double) {
                output = ((Double) numVal).intValue();
            } else if (numVal instanceof Integer) {
                output = (Integer) numVal;
            } else if (numVal instanceof String) {
                output = Integer.valueOf((String) numVal);
            }
        }
        return output;
    }
    
    public Double convertKelvinToCelsius(Double kelvinTemperature){
        return kelvinTemperature - 273.15;
    }
    public boolean hasTemp() {
        return !Double.isNaN(this.temp);
    }

    public boolean hasTempMin() {
        return !Double.isNaN(this.tempMin);
    }

    public boolean hasTempMax() {
        return !Double.isNaN(this.tempMax);
    }

    public boolean hasPressure() {
        return !Double.isNaN(this.pressure);
    }

    public boolean hasHumidity() {
        return !Double.isNaN(this.humidity);
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
