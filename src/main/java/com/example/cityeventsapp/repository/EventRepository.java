package com.example.cityeventsapp.repository;

import com.example.cityeventsapp.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface EventRepository extends org.springframework.data.jpa.repository.JpaRepository<Event, Long> {

    @Query("""
    SELECT e
    FROM Event e
    WHERE e.eventTime >= :now
      AND (:q IS NULL OR :q = '' OR
           LOWER(e.title) LIKE LOWER(CONCAT('%', :q, '%')) OR
           LOWER(e.location) LIKE LOWER(CONCAT('%', :q, '%')))
      AND e.eventTime >= COALESCE(:from, e.eventTime)
      AND e.eventTime <= COALESCE(:to, e.eventTime)
    ORDER BY e.eventTime ASC
""")
    Page<Event> searchUpcoming(
            @Param("now") LocalDateTime now,
            @Param("q") String q,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            Pageable pageable
    );
}


