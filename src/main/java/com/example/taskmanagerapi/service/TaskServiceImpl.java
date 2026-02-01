package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.DTO.TaskCreateDto;
import com.example.taskmanagerapi.DTO.TaskResponseDto;
import com.example.taskmanagerapi.DTO.TaskUpdateDto;
import com.example.taskmanagerapi.Domain.Exception.InvalidTaskException;
import com.example.taskmanagerapi.Domain.Exception.TaskNotFoundException;
import com.example.taskmanagerapi.Domain.Model.Task;
import com.example.taskmanagerapi.Domain.Status;
import com.example.taskmanagerapi.infra.persistence.TaskMapper;
import com.example.taskmanagerapi.infra.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    @Override
    public TaskResponseDto createTask(TaskCreateDto dto) {
        checkIfTitleValid(dto.getTitle());
        checkIfDueDateValid(dto.getDueDate());
        Task task = taskMapper.toEntity(dto);
        taskRepository.save(task);
        return taskMapper.toResponseDto(task);
    }

    private static void checkIfTitleValid(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidTaskException("Title is mandatory");
        }
    }

    private static void checkIfDueDateValid(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new InvalidTaskException("The due date cannot be in the past");
        }
    }

    @Override
    public TaskResponseDto getTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        return taskMapper.toResponseDto(task);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.toResponseDtoList(tasks);
    }

    @Override
    public TaskResponseDto updateTask(UUID id, TaskUpdateDto dto) {
        Task taskToUpdate = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        checkIfTitleValid(dto.getTitle());
        updateStatusToDone(taskToUpdate, dto);
        Task taskUpdated = taskMapper.updateEntity(dto, taskToUpdate);
        taskRepository.save(taskUpdated);
        return taskMapper.toResponseDto(taskUpdated);
    }

    private void updateStatusToDone(Task task, TaskUpdateDto dto) {
        if(task.getStatus().equals(Status.IN_PROGRESS) && dto.getStatus().equals(Status.DONE)) {
            task.setStatus(Status.DONE);
        }
    }

    @Override
    public void deleteTask(UUID id) {
        Task taskToDelete = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        taskRepository.deleteById(taskToDelete.getId());
    }
}
