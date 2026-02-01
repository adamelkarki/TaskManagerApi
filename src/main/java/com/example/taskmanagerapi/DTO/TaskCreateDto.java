package com.example.taskmanagerapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class TaskCreateDto {

    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDAte;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
