package edu.glyndwr.weatherapp.frontend.factories;

import edu.glyndwr.weatherapp.backend.weatherservice.integration.model.implementations.WeatherData;
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
        SimpleDateFormat format = new SimpleDateFormat(WeatherData.WEATHERDATA_DATE_FORMAT, Locale.ENGLISH);
        int row = 0;
        if (!isForecast) {
            pane.addRow(row++, new Label("Weather today:"));
        } else {
            pane.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
        if (null != weatherdata.getWeatherIcon()) {
            ImageView weatherImage = new ImageView(new Image(WeatherData.WEATHERDATA_IMAGE_PATH + weatherdata.getWeatherIcon() + WeatherData.WEATHERDATA_IMAGE_FILETYPE));
            pane.addRow(row++, weatherImage, new Label(format.format(Date.from(weatherdata.getTimestamp()))), new Label(String.valueOf(weatherdata.getMainWeather())), new Label(String.valueOf(weatherdata.getMainWeatherDescription())));
        } else {
            Label na = new Label(WeatherData.NOT_AVAILABLE);
            pane.addRow(row++, na, new Label(WeatherData.NOT_AVAILABLE), new Label(String.valueOf(weatherdata.getMainWeather())), new Label(String.valueOf(weatherdata.getMainWeatherDescription())));
        }
        pane.addRow(row++, new Label("Temperature: "), new Label(String.format("%.2f",weatherdata.convertKelvinToCelsius(weatherdata.getTemp())) + " " + WeatherData.TEMPERATURE_CELSIUS_UNIT), new Label("Max Temperature: "), new Label(String.format("%.2f",weatherdata.convertKelvinToCelsius(weatherdata.getTempMax())) + " " + WeatherData.TEMPERATURE_CELSIUS_UNIT), new Label("Min Temperature: "), new Label(String.format("%.2f",weatherdata.convertKelvinToCelsius(weatherdata.getTempMin())) + " " + WeatherData.TEMPERATURE_CELSIUS_UNIT), new Label("Pressure: "), new Label(String.valueOf(weatherdata.getPressure()) + " " + WeatherData.PRESSURE_UNIT));
        pane.addRow(row++, new Label("Humidity: "), new Label(String.valueOf(weatherdata.getHumidity())+ " " + WeatherData.HUMIDITY_UNIT), new Label("Windspeed: "), new Label(String.valueOf(weatherdata.getSpeed())+ " " + WeatherData.WINDSPEED_UNIT), new Label("Wind gust: "), new Label(String.valueOf(weatherdata.getGust())+ " " + WeatherData.WINDSPEED_UNIT), new Label("Wind Direction: "), new Label(String.valueOf(weatherdata.getDeg())));
        if (!isForecast) {
            pane.addRow(row++, new Label(""));
            pane.addRow(row++, new Label("Weather Forecast:"));
        }

        return pane;
    }

}
