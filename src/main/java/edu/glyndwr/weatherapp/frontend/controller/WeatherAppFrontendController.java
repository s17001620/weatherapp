package edu.glyndwr.weatherapp.frontend.controller;

import edu.glyndwr.weatherapp.backend.weatherservice.controller.WeatherServiceController;
import edu.glyndwr.weatherapp.frontend.configuration.WeatherAppFrontendConfiguration;
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
    private Stage primaryStage;

    public void initializeStage(Stage primaryStage) {

        initializeFields();
        this.primaryStage = weatherServiceFrontendUIFactory.buildFrontendUI(this, primaryStage);
        this.primaryStage.show();
    }

    public void loadWeather(City city) {
        FlowPane pane = (FlowPane) primaryStage.getScene().lookup("#contentPane");
        pane.getChildren().remove(this.resultPane);
        this.resultPane = new GridPane();
        GridPane today = weatherServiceFrontendUIFactory.buildWeatherDataResultPaneForToday(weatherServiceController.getWeatherToday(city.getCountry(), city.getCity()));
        today.prefWidthProperty().bind(primaryStage.widthProperty());
        this.resultPane.addRow(0, today);
        if (!weatherServiceController.getWeatherToday(city.getCountry(), city.getCity()).getMainWeather().equals("CITY NOT FOUND!")) {
            GridPane forcasts = weatherServiceFrontendUIFactory.buildWeatherDataResultPaneForForcast(weatherServiceController.getWeatherForecast(city.getCountry(), city.getCity()));
            forcasts.prefWidthProperty().bind(primaryStage.widthProperty());
            this.resultPane.addRow(1, forcasts);
        }
        pane.getChildren().add(this.resultPane);

    }

    public void initializeFields() {
        countryBox = new ComboBox<>();
        cityBox = new ComboBox<>();
        resultPane = new GridPane();
    }
}
