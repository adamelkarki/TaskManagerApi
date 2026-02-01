package com.example.taskmanagerapi.DTO;

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
    private String status;
    private String priority;
    private LocalDate dueDAte;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
