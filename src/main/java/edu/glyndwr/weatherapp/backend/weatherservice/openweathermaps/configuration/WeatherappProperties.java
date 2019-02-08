

package edu.glyndwr.weatherapp.backend.weatherservice.openweathermaps.configuration;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("weatherapp")
public class WeatherappProperties {

	@Valid
	private final Api api = new Api();

	public Api getApi() {
		return this.api;
	}

	public static class Api {

		/**
		 * API key of the OpenWeatherMap service.
		 */
		@NotNull
                @Value("${openweathermap.key}")
		private String key;

		public String getKey() {
			return this.key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}

}
