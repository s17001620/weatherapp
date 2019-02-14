

package edu.glyndwr.weatherapp.backend.weatherservice.integration.model;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Getter
@Setter
public class WeatherToday extends WeatherData {

    public WeatherToday(){
    super();
}
    public WeatherToday(JSONObject json){
    super(json);
}
    private String name;

}
