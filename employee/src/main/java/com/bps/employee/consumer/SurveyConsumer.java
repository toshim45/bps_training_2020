package com.bps.employee.consumer;

import com.bps.employee.model.Employee;
import com.bps.employee.model.Survey;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SurveyConsumer {
    private static final Logger logger = LoggerFactory.getLogger(SurveyConsumer.class);

    private Employee employee = new Employee();

    @RabbitListener(queues = "surveys-created-employee")
    public void receive(String payload){
        try {
            Survey survey = new ObjectMapper().readValue(payload, Survey.class);
            int currentReward = employee.incrementRewards(survey.getSurveyorId());
            logger.debug("employee {} got reward {}", survey.getSurveyorId(), currentReward);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }
}
