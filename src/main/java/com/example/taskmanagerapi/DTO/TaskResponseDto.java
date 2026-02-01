package com.example.taskmanagerapi.DTO;

import com.example.taskmanagerapi.Domain.Priority;
import com.example.taskmanagerapi.Domain.Status;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class TaskResponseDto {

    private UUID id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
