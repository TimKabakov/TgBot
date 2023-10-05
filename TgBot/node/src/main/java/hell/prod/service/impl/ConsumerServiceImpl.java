package hell.prod.service.impl;

import hell.prod.service.ConsumerService;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import static hell.prod.model.RabbitQueue.*;

@Service
@Log4j
public class ConsumerServiceImpl implements ConsumerService {
    private final MainServiceImpl mainService;

    public ConsumerServiceImpl(MainServiceImpl mainService) {
        this.mainService = mainService;
    }


    @Override
    @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
    public void consumeTextMessageUpdate(Update update) {
        log.debug("Node: Text message is received");
        mainService.processTextMessage(update);
    }

}
