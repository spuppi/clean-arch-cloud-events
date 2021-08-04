package com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.model;

import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.enums.EventStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UseCaseEvent {

    private Object payload;
    private EventStatus status;
}
