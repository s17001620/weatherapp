package edu.glyndwr.weatherapp.frontend.factories;

import edu.glyndwr.weatherapp.frontend.controller.WeatherAppFrontendController;
import edu.glyndwr.weatherapp.frontend.model.City;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class CitySelectViewFactory {

    public GridPane buildWeatherUserControlls(WeatherAppFrontendController controller) {
        GridPane pane = new GridPane();

        controller.getCountryBox().getItems().addAll(controller.getWeatherAppFrontendConfiguration().getCountries().stream().sorted().collect(Collectors.toList()));
        controller.getCountryBox().setOnAction(event -> {
            controller.getCityBox().getItems().clear();
            ArrayList<City> cities = new ArrayList<>();
            cities.addAll(controller.getWeatherAppFrontendConfiguration().getCitiesForCountry(controller.getCountryBox().getValue()).stream().sorted((o1, o2) -> o1.getCity().compareTo(o2.getCity())).collect(Collectors.toList()));
            controller.getCityBox().getItems().addAll(cities);

        });

        controller.getCityBox().setOnAction(event -> {
            if (null != controller.getCityBox().getValue()) {
                controller.loadWeather(controller.getCityBox().getValue());
            }
        });
        pane.addRow(0, new Label("Country: "), controller.getCountryBox(), new Label("City: "), controller.getCityBox());

        return pane;
    }

}
