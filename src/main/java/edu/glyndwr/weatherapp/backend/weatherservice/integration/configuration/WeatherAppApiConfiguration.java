

package edu.glyndwr.weatherapp.backend.weatherservice.integration.configuration;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value="classpath:api.properties")
@Getter
@Setter
public class WeatherAppApiConfiguration {
                @Value("${openweathermap.key}")
		private String key;
}
