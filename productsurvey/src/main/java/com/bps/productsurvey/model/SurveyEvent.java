package com.bps.productsurvey.model;

public class SurveyEvent {
    public static final String ModusExchange = "survey-modus-exc";
    public static final String CreatedExchange = "survey-exc";
    public static final String ModusRoutingKey = "surveys.modus";
    public static final String CreatedRoutingKey = "surveys.created";
    public static final String CreatedQueue = "surveys-created";
}
