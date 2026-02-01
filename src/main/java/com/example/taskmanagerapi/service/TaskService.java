package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.DTO.TaskCreateDto;
import com.example.taskmanagerapi.DTO.TaskResponseDto;
import com.example.taskmanagerapi.DTO.TaskUpdateDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponseDto createTask(TaskCreateDto dto);
    TaskResponseDto getTaskById(UUID id);
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto updateTask(UUID id, TaskUpdateDto dto);
    void deleteTask(UUID id);
}

