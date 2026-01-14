package com.example.cityeventsapp.service.impl;

import com.example.cityeventsapp.model.Event;
import com.example.cityeventsapp.repository.EventRepository;
import com.example.cityeventsapp.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Page<Event> searchUpcoming(String q, LocalDateTime from, LocalDateTime to, int page, int size) {
        if (page < 0) page = 0;
        if (size < 3) size = 3;
        if (size > 24) size = 24;

        String query = (q == null || q.trim().isEmpty()) ? null : q.trim();

        Pageable pageable = PageRequest.of(page, size);
        return eventRepository.searchUpcoming(LocalDateTime.now(), query, from, to, pageable);
    }

    @Override
    public Event getByIdOrNull(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
}
