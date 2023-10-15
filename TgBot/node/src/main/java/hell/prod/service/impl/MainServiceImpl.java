package hell.prod.service.impl;


import com.hazelcast.client.impl.clientside.HazelcastClientInstanceImpl;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import hell.prod.entity.City;
import hell.prod.integration.WeatherIntegration;
import hell.prod.service.MainService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Log4j
@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final ProducerServiceImpl producerService;

    WeatherIntegration weatherIntegration = new WeatherIntegration(WebClient.builder().build());

    @Autowired
    private final HazelcastInstance hazelcastInstance;



    @Override
    public void processTextMessage(Update update) {
        String text = update.getMessage().getText();
        text.toLowerCase();
        Long chatID = update.getMessage().getChatId();
        try{
            if(hazelcastInstance.getMap("citiesMap").get(text) != null){
                City city = (City)hazelcastInstance.getMap("citiesMap").get(text);
                if(LocalDateTime.now().isBefore(city.getLocalDateTime().plusHours(1))){
                    String output = hazelcastInstance.getMap("citiesMap").get(text).toString();
                    log.debug(output);
                    log.debug("Из кэша");
                    sendAnswer(output, chatID);
                }else {
                    City newCity = weatherIntegration.getWeather(text);
                    hazelcastInstance.getMap("citiesMap").put(text, newCity);
                    String output = newCity.toString();
                    log.debug(output);
                    sendAnswer(output, chatID);
                }
            } else {
                City newCity = weatherIntegration.getWeather(text);
                hazelcastInstance.getMap("citiesMap").put(text, newCity);
                String output = newCity.toString();
                log.debug(output);
                sendAnswer(output, chatID);

            }
        } catch (Exception e){
            log.debug(e.toString());
            String answer = "Typo in city name";
            sendAnswer(answer, chatID);
        }

    }

    private void sendAnswer(String output, Long chatID) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(output);


        producerService.produceAnswer(sendMessage);
    }

}
