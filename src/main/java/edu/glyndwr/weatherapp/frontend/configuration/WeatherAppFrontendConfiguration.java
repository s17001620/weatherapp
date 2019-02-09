

package edu.glyndwr.weatherapp.frontend.configuration;

import edu.glyndwr.weatherapp.frontend.model.City;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Configuration
@PropertySource(value="classpath:city.properties")
@Getter
@Setter
public class WeatherAppFrontendConfiguration {
@Value("#{'${city.name}'.split(',')}")
	private List<String> citiesStrings;
private ArrayList<City> cities;
private ArrayList<String> countries;
@PostConstruct
  public void init(){
      cities = new ArrayList<>();
      citiesStrings.forEach(s -> {
          if(Arrays.asList(s.split("_")).size()>1){
          cities.add(new City(Arrays.asList(s.split("_")).get(0),Arrays.asList(s.split("_")).get(1)));
          }
          });
      countries = new ArrayList<>();
      cities.stream().filter(distinctByKey(City::getCountry)).forEach(c -> countries.add(c.getCountry()));
}
  
  private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
    }
  
  public ArrayList<City> getCitiesForCountry(String country){
      ArrayList<City> filteredCities= new ArrayList<>();
      filteredCities.addAll(cities.stream().filter(c->c.getCountry().equals(country)).collect(Collectors.toList()));
      return filteredCities;
  }
  
}
