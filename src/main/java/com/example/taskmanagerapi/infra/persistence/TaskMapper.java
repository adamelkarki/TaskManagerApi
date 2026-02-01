package com.example.taskmanagerapi.infra.persistence;

import com.example.taskmanagerapi.DTO.TaskCreateDto;
import com.example.taskmanagerapi.DTO.TaskRequestDto;
import com.example.taskmanagerapi.DTO.TaskResponseDto;
import com.example.taskmanagerapi.DTO.TaskUpdateDto;
import com.example.taskmanagerapi.Domain.Model.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public Task toEntity (TaskRequestDto taskResponseDto) {
        return Task.builder()
                .title(taskResponseDto.getTitle())
                .description(taskResponseDto.getDescription())
                .status(taskResponseDto.getStatus())
                .priority(taskResponseDto.getPriority())
                .dueDate(taskResponseDto.getDueDate())
                .build();
    }

    public Task toEntity (TaskCreateDto taskCreateDto) {
        return Task.builder()
                .title(taskCreateDto.getTitle())
                .description(taskCreateDto.getDescription())
                .priority(taskCreateDto.getPriority())
                .status(taskCreateDto.getStatus())
                .dueDate(taskCreateDto.getDueDate())
                .createdAt(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
    }

    public Task updateEntity(TaskUpdateDto taskUpdateDto, Task task) {
        task.setTitle(taskUpdateDto.getTitle());
        task.setDescription(taskUpdateDto.getDescription());
        task.setStatus(taskUpdateDto.getStatus());
        task.setPriority(taskUpdateDto.getPriority());
        task.setDueDate(taskUpdateDto.getDueDate());
        task.setUpdated_at(LocalDateTime.now());
        return task;
    }

    public TaskResponseDto toResponseDto(Task task) {
        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdated_at())
                .build();
    }

    public List<TaskResponseDto> toResponseDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }


}
