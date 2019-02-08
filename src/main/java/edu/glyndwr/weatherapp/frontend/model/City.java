

package edu.glyndwr.weatherapp.frontend.model;

import lombok.Data;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Data
public class City {
    private String country;
    private String city;

    public City(){
        
    }
    public City(String city, String country){
        this.city = city;
        this.country = country;
    }
    
    @Override
    public String toString(){
        return this.city+", "+this.country;
    }
}
