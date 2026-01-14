package com.example.cityeventsapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventUpdateRequest {

    @NotBlank
    @Size(max = 255)
    private String title;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventTime;

    @NotBlank
    @Size(max = 255)
    private String location;

    @Size(max = 5000)
    private String description;

    @Size(max = 500)
    private String imageUrl;
}
