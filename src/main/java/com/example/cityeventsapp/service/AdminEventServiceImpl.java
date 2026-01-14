package com.example.cityeventsapp.service.impl;

import com.example.cityeventsapp.dto.EventCreateRequest;
import com.example.cityeventsapp.dto.EventUpdateRequest;
import com.example.cityeventsapp.mapper.EventMapper;
import com.example.cityeventsapp.model.Event;
import com.example.cityeventsapp.repository.EventRepository;
import com.example.cityeventsapp.service.AdminEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminEventServiceImpl implements AdminEventService {

    private final EventRepository eventRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Event> list(int page, int size) {
        if (page < 0) page = 0;
        if (size < 5) size = 5;
        if (size > 50) size = 50;

        var pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "eventTime"));
        return eventRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Event create(EventCreateRequest req) {
        Event event = EventMapper.toEntity(req);
        return eventRepository.save(event);
    }

    @Override
    @Transactional(readOnly = true)
    public Event getByIdOrNull(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public EventUpdateRequest getUpdateDtoOrNull(Long id) {
        return eventRepository.findById(id)
                .map(EventMapper::toUpdateRequest)
                .orElse(null);
    }

    @Override
    @Transactional
    public boolean update(Long id, EventUpdateRequest req) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) return false;

        EventMapper.applyUpdate(event, req);
        eventRepository.save(event);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!eventRepository.existsById(id)) return false;
        eventRepository.deleteById(id);
        return true;
    }
}
