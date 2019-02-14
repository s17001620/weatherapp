

package edu.glyndwr.weatherapp.frontend.factories;

import edu.glyndwr.weatherapp.backend.weatherservice.integration.model.implementations.Forecast;
import edu.glyndwr.weatherapp.backend.weatherservice.integration.model.implementations.WeatherData;
import edu.glyndwr.weatherapp.frontend.controller.WeatherAppFrontendController;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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
        root.setId("root");
        root.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-border-radius: 5;");
        FlowPane pane = new FlowPane();
        pane.setId("contentPane");
        controller.getResultPane().setId("resultPane");
        pane.getChildren().addAll(citySelectViewFactory.buildWeatherUserControlls(controller), controller.getResultPane());
        
        root.setSpacing(5);
        root.setMinWidth(830);
        root.setMinHeight(1000);
        final ScrollPane scroll = new ScrollPane();
        scroll.prefWidthProperty().bind(stage.widthProperty());
        scroll.prefHeightProperty().bind(stage.heightProperty());
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        scroll.setContent(pane);
        scroll.viewportBoundsProperty().addListener((ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) -> {
            pane.setPrefWidth(bounds.getWidth());
            pane.setPrefHeight(bounds.getHeight());
        });
        root.getChildren().addAll(scroll);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Weather App");
        return stage;
    }
 
 public GridPane buildWeatherDataResultPaneForToday(WeatherData weatherData){
     return weatherDataDisplayViewFactory.generateWeatherDataPane(weatherData, false,"");
 }
  public GridPane buildWeatherDataResultPaneForForcast(Forecast forecast){
      GridPane forcastPane = new GridPane();
      
      SimpleDateFormat format = new SimpleDateFormat("EEEE, dd/MM/yyyy", Locale.ENGLISH);
      String datestring =format.format(Date.from(forecast.getEntries().get(0).getTimestamp()));
      String startbackground ="";
      int period = 1;
      for(WeatherData w: forecast.getEntries()){
        String thisdate = format.format(Date.from(w.getTimestamp()));
          if(!thisdate.equals(datestring)){
            datestring=thisdate;
            period++;
          }
             if(period%2!=0){
             startbackground ="white";
             }else{
               startbackground ="lightgrey";   
             }
          forcastPane.addRow(forecast.getEntries().indexOf(w),weatherDataDisplayViewFactory.generateWeatherDataPane(w,true, startbackground));
          
      
      }
      return forcastPane;
  }


}
