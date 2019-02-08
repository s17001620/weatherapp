

package edu.glyndwr.weatherapp.frontend.controller;

import edu.glyndwr.weatherapp.backend.weatherservice.controller.WeatherServiceController;
import edu.glyndwr.weatherapp.frontend.configuration.WeatherAppFrontendConfiguration;
import edu.glyndwr.weatherapp.frontend.factories.WeatherDataDisplayViewFactory;
import edu.glyndwr.weatherapp.frontend.factories.WeatherServiceFrontendUIFactory;
import edu.glyndwr.weatherapp.frontend.model.City;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 *
 * @author Alexander Bruckbauer s17001620
 */

@Getter
@Setter
@Component
public class WeatherAppFrontendController {
@Autowired
private WeatherServiceController weatherServiceController;
@Autowired
private WeatherServiceFrontendUIFactory weatherServiceFrontendUIFactory;
@Autowired
private WeatherAppFrontendConfiguration weatherAppFrontendConfiguration;

        
private ComboBox<String> countryBox;
private ComboBox<City> cityBox;
private GridPane resultPane;
    
    public void initializeStage(Stage primaryStage){
        initializeFields();
        weatherServiceFrontendUIFactory.buildFrontendUI(this, primaryStage).show();
    }

    public void loadWeather(City city) {
        this.resultPane = weatherServiceFrontendUIFactory.buildWeatherDataResultPaneForToday(weatherServiceController.getWeatherToday(city.getCity(), city.getCountry()));
    }
    
    public void initializeFields(){
        countryBox = new ComboBox<>();
        cityBox = new ComboBox<>();
        resultPane = new GridPane();
    }
}
