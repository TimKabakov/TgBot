package hell.prod.integration;

import hell.prod.entity.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Log4j
@Service
@RequiredArgsConstructor
public class WeatherIntegration {
    private final WebClient webClient;
    private final String key = "a4a770e704b1483aa3394258230403";



    public City getWeather(String name){
        City city = new City();
        String uri = ("http://api.weatherapi.com/v1/forecast.json?key="+ key +"&q="+ name +"&days=2&aqi=no&alerts=no");
        log.debug(uri);
        JSONObject weatherInfo = new JSONObject(webClient.get().uri(uri).retrieve().bodyToMono(String.class).block());
        log.debug(weatherInfo.toString());
        city = converter(weatherInfo);
        return city;
    }
    public String getInfo(String name){
        String uri = ("http://api.weatherapi.com/v1/forecast.json?key="+ key +"&q="+ name +"&days=2&aqi=no&alerts=no");
        log.debug(uri);
        JSONObject weatherInfo = new JSONObject(webClient.get().uri(uri).retrieve().bodyToMono(String.class).block());
        log.debug(weatherInfo.toString());
        City city = converter(weatherInfo);
        return city.toString();
    }

    private City converter(JSONObject weatherInfo) {
        City weatherInCity = new City();
        JSONObject location = new JSONObject(weatherInfo.getJSONObject("location").toString());
        weatherInCity.setName(location.getString("name"));
        weatherInCity.setCountry(location.getString("country"));
        JSONObject current = weatherInfo.getJSONObject("current");
        weatherInCity.setWeatherCurrent(current.getDouble("temp_c"));
        weatherInCity.setWeatherCurrentFeels(current.getDouble("feelslike_c"));
        weatherInCity.setWindDir(current.getString("wind_dir"));
        weatherInCity.setWindSpeed(current.getDouble("wind_kph"));
        JSONObject condition = current.getJSONObject("condition");
        weatherInCity.setCondition(condition.getString("text"));
        weatherInCity.setPressure(current.getDouble("pressure_mb"));
        JSONObject forecast = weatherInfo.getJSONObject("forecast");
        JSONArray days = forecast.getJSONArray("forecastday");
        JSONObject today = days.getJSONObject(0);
        JSONObject day1 = today.getJSONObject("day");
        weatherInCity.setUv(day1.getDouble("uv"));
        JSONObject tomorrow = days.getJSONObject(1);
        String date = tomorrow.getString("date");
        String[] data = date.split( "-");
        String fcDate = data[2]+ "." +data[1] +"." +data[0];
        weatherInCity.setForecastDate(fcDate);
        JSONObject day = tomorrow.getJSONObject("day");
        weatherInCity.setWeatherForecastAvg(day.getDouble("avgtemp_c"));
        weatherInCity.setWeatherForecastMax(day.getDouble("maxtemp_c"));
        weatherInCity.setWeatherForecastMin(day.getDouble("mintemp_c"));
        JSONObject conditionForNextDay = day.getJSONObject("condition");
        weatherInCity.setConditionForecast(conditionForNextDay.getString("text"));
        weatherInCity.setWindSpeedForecast(day.getDouble("maxwind_kph"));
        weatherInCity.setUvForecast(day.getDouble("uv"));
        return weatherInCity;
    }

}
