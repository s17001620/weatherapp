

package edu.glyndwr.weatherapp.frontend.factories;

import edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.WeatherData;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class WeatherDataDisplayViewFactory {
    
    public GridPane generateWeatherDataPane(WeatherData weatherdata){
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(5);
        pane.addRow(0, new Label("General Weather Information: "));
        pane.addRow(1, new Label("Temperature: "), new Label (String.valueOf(weatherdata.getTemp())), new Label("Max Temperature: "), new Label (String.valueOf(weatherdata.getTempMax())), new Label("Min Temperature: "), new Label (String.valueOf(weatherdata.getTempMin())));
        pane.addRow(2, new Label("Pressure: "), new Label (String.valueOf(weatherdata.getPressure())), new Label("Humidity: "), new Label (String.valueOf(weatherdata.getHumidity())), new Label("Min Temperature: "), new Label (weatherdata.getWeatherIcon()));
        pane.addRow(3, new Label("Wind Information: "));
        pane.addRow(4, new Label("Windspeed: "), new Label (String.valueOf(weatherdata.getSpeed())), new Label("Wind gust: "), new Label (String.valueOf(weatherdata.getGust())), new Label("Wind Direction: "), new Label (String.valueOf(weatherdata.getDeg())));
        return pane;
    }

}
