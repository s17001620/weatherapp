package edu.glyndwr.weatherapp;

import edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.configuration.WeatherappProperties;
import edu.glyndwr.weatherapp.frontend.controller.WeatherAppFrontendController;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@Log
@SpringBootApplication
@ComponentScan("edu.glyndwr")
@EnableConfigurationProperties(WeatherappProperties.class)
public class WeatherappApplication extends Application{
 private ConfigurableApplicationContext context;
    @Autowired
    private WeatherAppFrontendController weatherAppFrontendController;
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        weatherAppFrontendController = (WeatherAppFrontendController) context.getBean(WeatherAppFrontendController.class);
        weatherAppFrontendController.initializeStage(primaryStage);
    }
    
    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(WeatherappApplication.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));
    }
    
      @Override
    public void stop() throws Exception {
        context.close();
    }

}

