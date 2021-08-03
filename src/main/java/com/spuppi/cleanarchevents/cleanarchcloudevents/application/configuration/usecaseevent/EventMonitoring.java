package com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EventMonitoring {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ObjectMapper objectMapper;

    @Async
    @EventListener
    public void monitoring(UseCaseEvent event){
        System.out.println("monitoring");
        System.out.println(event);
        eventPublisher.publishEvent(objectMapper.convertValue(event.getPayload(), event.getEventType()));
    }
}
