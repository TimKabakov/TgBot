package hell.prod.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {


    String forecastDate;
    String name;
    String country;
    String lat;
    String lon;
    Double weatherCurrent;
    Double weatherCurrentFeels;
    String condition;
    Double weatherForecastMax;
    Double weatherForecastAvg;
    Double weatherForecastMin;
    Double windSpeed;
    Double windSpeedForecast;
    String windDir;
    String conditionForecast;
    Double pressure;
    Double uv;
    Double uvForecast;
    LocalDateTime localDateTime;
    String icon;


    @Override
    public String toString() {
        return name + ", " + country +
                ", current weather: " + weatherCurrent +
                ", feels like " + weatherCurrentFeels +
                ", wind " + windSpeed +
                " kph" +
                ", wind direction " + windDir +
                ", conditions " + condition + icon +
                ", pressure " + pressure +
                ", uv " + uv + ". \n" +
                "Forecast for " + forecastDate +
                " is " + weatherForecastAvg +
                ", maximum " + weatherForecastMax +
                ", minimum " + weatherForecastMin +
                ", maximum wind speed is " + windSpeedForecast +
                " kph" +
                ", conditions " + conditionForecast +
                ", uv " + uvForecast;
    }
}