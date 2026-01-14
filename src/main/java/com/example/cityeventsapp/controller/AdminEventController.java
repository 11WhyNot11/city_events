package com.example.cityeventsapp.controller;

import com.example.cityeventsapp.dto.EventCreateRequest;
import com.example.cityeventsapp.dto.EventUpdateRequest;
import com.example.cityeventsapp.service.AdminEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class AdminEventController {

    private final AdminEventService adminEventService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       Model model) {

        var eventsPage = adminEventService.list(page, size);
        model.addAttribute("eventsPage", eventsPage);
        model.addAttribute("size", size);

        return "admin/events/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("mode", "create");
        model.addAttribute("eventId", null);
        model.addAttribute("event", new EventCreateRequest());
        return "admin/events/form";
    }

    @PostMapping
    public String create(@ModelAttribute("event") @Valid EventCreateRequest req,
                         BindingResult br,
                         Model model,
                         RedirectAttributes ra) {

        if (br.hasErrors()) {
            model.addAttribute("mode", "create");
            model.addAttribute("eventId", null);
            return "admin/events/form";
        }

        adminEventService.create(req);
        ra.addFlashAttribute("success", "Подію створено.");
        return "redirect:/admin/events";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        var dto = adminEventService.getUpdateDtoOrNull(id);
        if (dto == null) {
            ra.addFlashAttribute("error", "Подію не знайдено.");
            return "redirect:/admin/events";
        }

        model.addAttribute("mode", "edit");
        model.addAttribute("eventId", id);
        model.addAttribute("event", dto);
        return "admin/events/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("event") @Valid EventUpdateRequest req,
                         BindingResult br,
                         Model model,
                         RedirectAttributes ra) {

        if (br.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("eventId", id);
            return "admin/events/form";
        }

        boolean updated = adminEventService.update(id, req);
        if (!updated) {
            ra.addFlashAttribute("error", "Подію не знайдено.");
            return "redirect:/admin/events";
        }

        ra.addFlashAttribute("success", "Зміни збережено.");
        return "redirect:/admin/events";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        boolean deleted = adminEventService.delete(id);
        if (!deleted) {
            ra.addFlashAttribute("error", "Подію не знайдено.");
            return "redirect:/admin/events";
        }
        ra.addFlashAttribute("success", "Подію видалено.");
        return "redirect:/admin/events";
    }
}
