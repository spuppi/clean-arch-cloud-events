package com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.model;

import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.enums.EventStatus;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.enums.EventType;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

public class UseCaseEvent {

    private Map<String, String> headers = new HashMap<String, String>();
    private Object payload;
    private EventType eventType;
    private EventStatus status;

    public UseCaseEvent() {}

    public UseCaseEvent(Map<String, String> headers, Object payload, EventStatus status) {
        this.headers = headers;
        this.payload = payload;
        this.status = status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "UseCaseEvent{" +
                "headers=" + headers +
                ", payload=" + payload +
                ", eventType=" + eventType +
                ", status=" + status +
                '}';
    }
}
