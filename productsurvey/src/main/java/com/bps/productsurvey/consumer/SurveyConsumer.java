package com.bps.productsurvey.consumer;

import com.bps.productsurvey.model.SurveyEvent;
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

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = SurveyEvent.CreatedQueue, durable = "true"),
            exchange = @Exchange(value=SurveyEvent.CreatedExchange,type = "fanout",durable = "true"),
            key = SurveyEvent.CreatedRoutingKey,
            declare = "true"
    ))
    public void receive(String payload){
        surveyService.calculateModus();
    }
}
