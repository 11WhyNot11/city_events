package com.example.cityeventsapp.mapper;

import com.example.cityeventsapp.dto.EventCreateRequest;
import com.example.cityeventsapp.dto.EventUpdateRequest;
import com.example.cityeventsapp.model.Event;

public final class EventMapper {

    private EventMapper() {}

    public static Event toEntity(EventCreateRequest req) {
        return Event.builder()
                .title(req.getTitle())
                .eventTime(req.getEventTime())
                .location(req.getLocation())
                .description(req.getDescription())
                .imageUrl(req.getImageUrl())
                .build();
    }

    public static void applyUpdate(Event event, EventUpdateRequest req) {
        event.setTitle(req.getTitle());
        event.setEventTime(req.getEventTime());
        event.setLocation(req.getLocation());
        event.setDescription(req.getDescription());
        event.setImageUrl(req.getImageUrl());
    }

    public static EventUpdateRequest toUpdateRequest(Event event) {
        return EventUpdateRequest.builder()
                .title(event.getTitle())
                .eventTime(event.getEventTime())
                .location(event.getLocation())
                .description(event.getDescription())
                .imageUrl(event.getImageUrl())
                .build();
    }
}
