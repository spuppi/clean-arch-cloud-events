package com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent;

import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.enums.StatusEvent;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UseCaseEvent {

    private Class<?> eventType;
    private Object payload;
    private StatusEvent status;
}
