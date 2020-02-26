package com.bps.productsurvey.consumer;

import com.bps.productsurvey.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyConsumer {
    private static final Logger logger = LoggerFactory.getLogger(SurveyConsumer.class);

    @Autowired
    SurveyService surveyService;

    @RabbitListener(queues = "surveys-created")
    public void receive(String payload){
        surveyService.calculateModus();
    }

    @RabbitListener(bindings = @QueueBinding(
            key = "survey-modus-key-1",
            value=@Queue(
                    value = "surveys-modus-1",
                    durable = "false"
            ),
            exchange = @Exchange(value="survey-modus-exc",type = "fanout",durable = "true"),
            declare = "true"
    ))
    public void receive2(String payload){
        logger.debug("receive-2: " + payload);
    }
}
