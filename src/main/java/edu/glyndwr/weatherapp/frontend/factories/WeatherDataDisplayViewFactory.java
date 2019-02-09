package edu.glyndwr.weatherapp.frontend.factories;

import edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.WeatherData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class WeatherDataDisplayViewFactory {

    public GridPane generateWeatherDataPane(WeatherData weatherdata, Boolean isForecast, String background) {
        GridPane pane = new GridPane();
        if (background.isEmpty()) {
            pane.setStyle("-fx-background-fill: grey, white ;");
        } else {
            pane.setStyle("-fx-background-color: " + background + ";");
        }

        pane.setHgap(10);
        pane.setVgap(5);
        SimpleDateFormat format = new SimpleDateFormat("EEEE, dd/MM/yyyy HH Z", Locale.ENGLISH);
        int row = 0;
        if (!isForecast) {
            pane.addRow(row++, new Label("Weather today:"));
        } else {
            pane.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
        if (null != weatherdata.getWeatherIcon()) {
            ImageView weatherImage = new ImageView(new Image("http://openweathermap.org/img/w/" + weatherdata.getWeatherIcon() + ".png"));
            pane.addRow(row++, weatherImage, new Label(format.format(Date.from(weatherdata.getTimestamp()))), new Label(String.valueOf(weatherdata.getMainWeather())), new Label(String.valueOf(weatherdata.getMainWeatherDescription())));
        } else {
            Label na = new Label("NOT AVAILABLE");
            pane.addRow(row++, na, new Label("n/a"), new Label(String.valueOf(weatherdata.getMainWeather())), new Label(String.valueOf(weatherdata.getMainWeatherDescription())));
        }
        pane.addRow(row++, new Label("Temperature: "), new Label(String.valueOf(weatherdata.getTemp())), new Label("Max Temperature: "), new Label(String.valueOf(weatherdata.getTempMax())), new Label("Min Temperature: "), new Label(String.valueOf(weatherdata.getTempMin())), new Label("Pressure: "), new Label(String.valueOf(weatherdata.getPressure())));
        pane.addRow(row++, new Label("Humidity: "), new Label(String.valueOf(weatherdata.getHumidity())), new Label("Windspeed: "), new Label(String.valueOf(weatherdata.getSpeed())), new Label("Wind gust: "), new Label(String.valueOf(weatherdata.getGust())), new Label("Wind Direction: "), new Label(String.valueOf(weatherdata.getDeg())));
        if (!isForecast) {
            pane.addRow(row++, new Label(""));
            pane.addRow(row++, new Label("Weather Forecast:"));
        }
        return pane;
    }

}
