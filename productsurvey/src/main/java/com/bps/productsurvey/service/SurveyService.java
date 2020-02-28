package com.bps.productsurvey.service;

import com.bps.productsurvey.model.Survey;
import com.bps.productsurvey.model.SurveyEvent;
import com.bps.productsurvey.model.SurveyModus;
import com.bps.productsurvey.repository.SurveyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import java.util.List;

@Service
public class SurveyService {
    private static final Logger logger = LoggerFactory.getLogger(SurveyService.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    SurveyRepository repository;



    public void create(Survey survey){
        repository.insert(survey);
        try {
            sendCreatedEvent(survey);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }

    public List<SurveyModus> summaryModus() {
        return repository.calculateModus();
    }

    private void sendCreatedEvent(Survey survey) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(survey);
        Message message = MessageBuilder.withBody(json.getBytes()).build();
        rabbitTemplate.send(SurveyEvent.CreatedExchange, SurveyEvent.CreatedRoutingKey, message);
        logger.debug("survey-created", json);
    }

    private void sendSummaryModusEvent(List<SurveyModus> modus) throws JsonProcessingException {
        declareSummaryModus();
        String json = new ObjectMapper().writeValueAsString(modus);
        Message message = MessageBuilder.withBody(json.getBytes()).build();
        rabbitTemplate.send(SurveyEvent.ModusExchange, SurveyEvent.ModusRoutingKey, message);
        logger.debug("modus-summarized", json);
    }

    private void declareSummaryModus() {
        Exchange exchange = new FanoutExchange(SurveyEvent.ModusExchange);
        new RabbitAdmin(rabbitTemplate).declareExchange(exchange);
    }

    public void calculateModus() {
        try {
            sendSummaryModusEvent(summaryModus());
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }
}
