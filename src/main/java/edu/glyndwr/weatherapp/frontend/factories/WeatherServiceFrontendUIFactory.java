

package edu.glyndwr.weatherapp.frontend.factories;

import edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.Forecast;
import edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.integration.WeatherData;
import edu.glyndwr.weatherapp.frontend.controller.WeatherAppFrontendController;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Component
public class WeatherServiceFrontendUIFactory {
    @Autowired
    private CitySelectViewFactory citySelectViewFactory;
    @Autowired
    private WeatherDataDisplayViewFactory weatherDataDisplayViewFactory;
    
    
 public Stage buildFrontendUI(WeatherAppFrontendController controller, Stage stage) {
        VBox root = new VBox();
        root.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-border-radius: 5;");
        FlowPane pane = new FlowPane();
        pane.getChildren().addAll(citySelectViewFactory.buildWeatherUserControlls(controller), controller.getResultPane());
        root.getChildren().addAll(pane);
        root.setSpacing(5);
        root.setMinWidth(800);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Weather App");
        return stage;
    }
 
 public GridPane buildWeatherDataResultPaneForToday(WeatherData weatherData){
     return weatherDataDisplayViewFactory.generateWeatherDataPane(weatherData);
 }
  public GridPane buildWeatherDataResultPaneForForcast(Forecast forecast){
      GridPane forcastPane = new GridPane();
      forecast.getEntries().forEach(f-> forcastPane.addRow(forecast.getEntries().indexOf(f),weatherDataDisplayViewFactory.generateWeatherDataPane(f)));
      return forcastPane;
  }


}
