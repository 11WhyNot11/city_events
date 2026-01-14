package com.example.cityeventsapp.service;

import com.example.cityeventsapp.dto.EventCreateRequest;
import com.example.cityeventsapp.dto.EventUpdateRequest;
import com.example.cityeventsapp.model.Event;
import org.springframework.data.domain.Page;

public interface AdminEventService {

    Page<Event> list(int page, int size);

    Event create(EventCreateRequest req);

    Event getByIdOrNull(Long id);

    EventUpdateRequest getUpdateDtoOrNull(Long id);

    boolean update(Long id, EventUpdateRequest req);

    boolean delete(Long id);
}
