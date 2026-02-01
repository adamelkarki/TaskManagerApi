package com.example.taskmanagerapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class TaskRequestDto {

    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;

}
