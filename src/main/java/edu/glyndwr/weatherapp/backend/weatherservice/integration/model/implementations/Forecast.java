

package edu.glyndwr.weatherapp.backend.weatherservice.integration.model.implementations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Getter
@Setter
public class Forecast implements Serializable{
	private String name;

	private List<WeatherData> entries = new ArrayList<>();

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("entries")
	public List<WeatherData> getEntries() {
		return this.entries;
	}

	@JsonSetter("list")
	public void setEntries(List<WeatherData> entries) {
		this.entries = entries;
	}

	@JsonProperty("city")
	public void setCity(Map<String, Object> city) {
		setName(city.get("name").toString());
	}
}
