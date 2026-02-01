package com.example.taskmanagerapi;

import com.example.taskmanagerapi.DTO.TaskCreateDto;
import com.example.taskmanagerapi.DTO.TaskResponseDto;
import com.example.taskmanagerapi.Domain.Exception.InvalidTaskException;
import com.example.taskmanagerapi.Domain.Exception.TaskNotFoundException;
import com.example.taskmanagerapi.Domain.Model.Task;
import com.example.taskmanagerapi.Domain.Priority;
import com.example.taskmanagerapi.Domain.Status;
import com.example.taskmanagerapi.infra.persistence.TaskMapper;
import com.example.taskmanagerapi.infra.repository.TaskRepository;
import com.example.taskmanagerapi.service.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    private TaskCreateDto validDto;
    private Task validTask;
    private TaskResponseDto expectedResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        validDto = new TaskCreateDto();
        validDto.setTitle("New Task");
        validDto.setDescription("Some description");
        validDto.setPriority(Priority.MEDIUM);
        validDto.setStatus(Status.TODO);
        validDto.setDueDate(LocalDate.now().plusDays(2));
        validDto.setCreatedAt(LocalDateTime.now());
        validDto.setUpdatedAt(LocalDateTime.now());

        validTask = new Task();
        validTask.setId(UUID.randomUUID());
        validTask.setTitle(validDto.getTitle());
        validTask.setDescription(validDto.getDescription());
        validTask.setPriority(validDto.getPriority());
        validTask.setStatus(validDto.getStatus());
        validTask.setDueDate(validDto.getDueDate());
        validTask.setCreatedAt(validDto.getCreatedAt());

        expectedResponse = TaskResponseDto.builder()
                .id(validTask.getId())
                .title(validTask.getTitle())
                .description(validTask.getDescription())
                .priority(validTask.getPriority())
                .status(validTask.getStatus())
                .dueDate(validTask.getDueDate())
                .createdAt(validTask.getCreatedAt())
                .build();
    }

    @Test
    void createTask_withValidData_shouldReturnResponseDto() {
        when(taskMapper.toEntity(validDto)).thenReturn(validTask);
        when(taskRepository.save(validTask)).thenReturn(validTask);
        when(taskMapper.toResponseDto(validTask)).thenReturn(expectedResponse);

        TaskResponseDto response = taskService.createTask(validDto);

        assertNotNull(response);
        assertEquals("New Task", response.getTitle());
        verify(taskRepository).save(validTask);
    }

    @Test
    void createTask_withEmptyTitle_shouldThrowException() {
        validDto.setTitle("");
        assertThrows(InvalidTaskException.class, () -> taskService.createTask(validDto));
    }

    @Test
    void createTask_withPastDueDate_shouldThrowException() {
        validDto.setDueDate(LocalDate.now().minusDays(1));
        assertThrows(InvalidTaskException.class, () -> taskService.createTask(validDto));
    }

    @Test
    void getTaskById_whenTaskExists_shouldReturnDto() {
        UUID id = validTask.getId();
        when(taskRepository.findById(id)).thenReturn(Optional.of(validTask));
        when(taskMapper.toResponseDto(validTask)).thenReturn(expectedResponse);

        TaskResponseDto response = taskService.getTaskById(id);

        assertNotNull(response);
        assertEquals(validTask.getTitle(), response.getTitle());
    }

    @Test
    void getTaskById_whenTaskDoesNotExist_shouldThrowException() {
        UUID id = UUID.randomUUID();
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(id));
    }
}
