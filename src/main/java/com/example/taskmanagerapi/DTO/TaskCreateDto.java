package com.example.taskmanagerapi.DTO;

import com.example.taskmanagerapi.Domain.Priority;
import com.example.taskmanagerapi.Domain.Status;
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
    private Priority priority;
    private Status status;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
