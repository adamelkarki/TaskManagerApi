package com.example.taskmanagerapi.infra.persistence;

import com.example.taskmanagerapi.DTO.TaskRequestDto;
import com.example.taskmanagerapi.DTO.TaskResponseDto;
import com.example.taskmanagerapi.DTO.TaskUpdateDto;
import com.example.taskmanagerapi.Domain.Model.Task;
import com.example.taskmanagerapi.Domain.Priority;
import com.example.taskmanagerapi.Domain.Status;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    Task toEntity (TaskRequestDto taskResponseDto) {
        return Task.builder()
                .title(taskResponseDto.getTitle())
                .description(taskResponseDto.getDescription())
                .status(Status.valueOf(taskResponseDto.getStatus()))
                .priority(Priority.valueOf(taskResponseDto.getPriority()))
                .dueDate(taskResponseDto.getDueDate())
                .build();
    }

    void updateEntity(TaskUpdateDto taskUpdateDto, Task task) {
        task.setTitle(taskUpdateDto.getTitle());
        task.setDescription(taskUpdateDto.getDescription());
        task.setStatus(Status.valueOf(taskUpdateDto.getStatus()));
        task.setPriority(Priority.valueOf(taskUpdateDto.getPriority()));
        task.setDueDate(taskUpdateDto.getDueDate());
    }

    TaskResponseDto toResponseDto(Task task) {
        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus().toString())
                .priority(task.getPriority().toString())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdated_at())
                .build();
    }

    List<TaskResponseDto> toResponseDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }


}
