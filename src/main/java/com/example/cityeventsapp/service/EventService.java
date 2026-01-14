package com.example.cityeventsapp.service;

import com.example.cityeventsapp.model.Event;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface EventService {

    Page<Event> searchUpcoming(String q,
                               LocalDateTime from,
                               LocalDateTime to,
                               int page,
                               int size);

    Event getByIdOrNull(Long id);
}
