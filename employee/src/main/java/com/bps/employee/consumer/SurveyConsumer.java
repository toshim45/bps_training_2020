package com.bps.employee.consumer;

import com.bps.employee.model.Employee;
import com.bps.employee.model.Survey;
import com.bps.employee.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyConsumer {
    private static final Logger logger = LoggerFactory.getLogger(SurveyConsumer.class);

    @Autowired
    private EmployeeService employeeService;

    @RabbitListener(queues = "surveys-created-employee")
    public void receive(String payload){
        try {
            Survey survey = new ObjectMapper().readValue(payload, Survey.class);
            Employee employee = new Employee(survey.getSurveyorId());
            employeeService.store(employee);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }
}
