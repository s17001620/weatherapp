package edu.glyndwr.weatherapp.backend.weatherservice.integration.service;

import edu.glyndwr.weatherapp.backend.weatherservice.integration.configuration.WeatherAppApiConfiguration;
import edu.glyndwr.weatherapp.backend.weatherservice.integration.model.implementations.Forecast;
import edu.glyndwr.weatherapp.backend.weatherservice.integration.model.implementations.WeatherToday;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Service
public class OpenWeatherMapService {

    private static final String WEATHER_URL
            = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";

    private static final String FORECAST_URL
            = "http://api.openweathermap.org/data/2.5/forecast?q={city},{country}&APPID={key}";

    private static final Logger logger = LoggerFactory.getLogger(OpenWeatherMapService.class);

    private final RestTemplate restTemplate;

    private final String apiKey;

    public OpenWeatherMapService(RestTemplateBuilder restTemplateBuilder,
            WeatherAppApiConfiguration properties) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiKey = properties.getKey();
    }

    public WeatherToday getWeatherToday(String country, String city) {
        logger.info("Requesting current weather for {}/{}", country, city);
        URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
        WeatherToday weather = new WeatherToday();
        try {
            weather = invoke(url, WeatherToday.class);
        } catch (Exception e) {
            weather.setDeg(0.0);
            weather.setGust(0.0);
            weather.setMainWeather(WeatherToday.CITY_NOT_FOUND);
            weather.setMainWeatherDescription(WeatherToday.CITY_NOT_FOUND);
            weather.setTemp(0.0);
            weather.setHumidity(0.0);
            weather.setSpeed(0.0);
            weather.setPressure(0.0);
            weather.setTempMax(0.0);
            weather.setTempMin(0.0);
        } finally {
            return weather;
        }
    }

    public Forecast getForecast(String country, String city) {
        logger.info("Requesting weather forecast for {}/{}", country, city);
        URI url = new UriTemplate(FORECAST_URL).expand(city, country, this.apiKey);
        return invoke(url, Forecast.class);
    }

    private <T> T invoke(URI url, Class<T> responseType) {
        RequestEntity<?> request = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<T> exchange = this.restTemplate
                .exchange(request, responseType);
        return exchange.getBody();
    }
}
