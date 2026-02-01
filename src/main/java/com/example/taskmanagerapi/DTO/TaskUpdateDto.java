package com.example.taskmanagerapi.DTO;

import com.example.taskmanagerapi.Domain.Priority;
import com.example.taskmanagerapi.Domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Setter
@Getter
public class TaskUpdateDto {

    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;

}
