package com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.model;

import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.enums.EventStatus;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UseCaseEvent {

    private Map<String, String> headers = new HashMap<String, String>();
    private Object payload;
    private EventStatus status;
}
