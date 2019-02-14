package edu.glyndwr.weatherapp.backend.weatherservice.intregration.controller;

import edu.glyndwr.weatherapp.backend.weatherservice.integration.model.Forecast;
import edu.glyndwr.weatherapp.backend.weatherservice.integration.model.OpenWeatherMapService;
import edu.glyndwr.weatherapp.backend.weatherservice.integration.model.WeatherToday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class WeatherServiceController {

    @Autowired
    private OpenWeatherMapService openWeatherMapService;
    
    public WeatherToday getWeatherToday(String country, String city){
        return openWeatherMapService.getWeatherToday(country,city);
    }
    
   public Forecast getWeatherForecast(String country, String city){
        return openWeatherMapService.getForecast(country,city);
    }
}
