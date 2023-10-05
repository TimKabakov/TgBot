package hell.prod.service.impl;


import com.hazelcast.client.impl.clientside.HazelcastClientInstanceImpl;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import hell.prod.entity.City;
import hell.prod.integration.WeatherIntegration;
import hell.prod.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.Resource;

@Log4j
@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final ProducerServiceImpl producerService;

    WeatherIntegration weatherIntegration = new WeatherIntegration(WebClient.builder().build());

    private final HazelcastInstance hazelcastInstance;





    @Override
    public void processTextMessage(Update update) {
        String text = update.getMessage().getText();
        text.toLowerCase();
        City newCity = weatherIntegration.getWeather(text);
        String output = newCity.toString();
        log.debug(output);
        Long chatID = update.getMessage().getChatId();
        sendAnswer(output, chatID);
//        if(hazelcastInstance.getMap("citiesMap").get(text) != null){
//            String output = hazelcastInstance.getMap("citiesMap").get(text).toString();
//            log.debug(output);
//            sendAnswer(output, chatID);
//        } else {
//            City newCity = weatherIntegration.getWeather(text);
//            hazelcastInstance.getMap("citiesMap").put(text, newCity);
//            String output = newCity.toString();
//            log.debug(output);
//            sendAnswer(output, chatID);
//        }

    }

    private void sendAnswer(String output, Long chatID) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(output);

        producerService.produceAnswer(sendMessage);
    }

}
