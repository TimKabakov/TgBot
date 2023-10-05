package hell.prod.entity;

import lombok.*;

import java.io.Serializable;

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


    @Override
    public String toString() {
        return name + ", " + country +
                ", current weather: " + weatherCurrent +
                ", feels like " + weatherCurrentFeels +
                ", wind " + windSpeed +
                ", wind direction " + windDir +
                ", conditions " + condition +
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