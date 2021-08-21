package com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.enums;

public enum EventType {

    AWS_API_GATEWAY("APIGatewayProxyRequestEvent");

    private String className;

    EventType(String className) {
        this.className = className;
    }
}
