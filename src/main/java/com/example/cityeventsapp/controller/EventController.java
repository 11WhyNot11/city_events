package com.example.cityeventsapp.controller;

import com.example.cityeventsapp.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public String list(@RequestParam(required = false) String q,
                       @RequestParam(required = false)
                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                       @RequestParam(required = false)
                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "6") int size,
                       Model model) {

        var eventsPage = eventService.searchUpcoming(q, from, to, page, size);

        model.addAttribute("eventsPage", eventsPage);
        model.addAttribute("q", q == null ? "" : q);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("size", size);

        return "events/list";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        var event = eventService.getByIdOrNull(id);
        if (event == null) return "redirect:/events";

        model.addAttribute("event", event);
        return "events/details";
    }
}
