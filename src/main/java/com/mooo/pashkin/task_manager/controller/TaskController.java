package com.mooo.pashkin.task_manager.controller;

import com.mooo.pashkin.task_manager.dto.CreateTaskRequest;
import com.mooo.pashkin.task_manager.dto.TaskResponse;
import com.mooo.pashkin.task_manager.dto.UpdateTaskStatusRequest;
import com.mooo.pashkin.task_manager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // 1. Создать задачу (POST /api/tasks) - возвращает 201 Created
    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest request) {
        TaskResponse created = taskService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // 2. Получить список задач (GET /api/tasks) - возвращает 200 OK
    @GetMapping
    public List<TaskResponse> getAll() {
        return taskService.findAll();
    }

    // 3. Получить одну задачу (GET /api/tasks/{id}) - возвращает 200 OK
    @GetMapping("/{id}")
    public TaskResponse getById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    // 4. Изменить статус (PATCH /api/tasks/{id}/status) - возвращает 200 OK
    @PatchMapping("/{id}/status")
    public TaskResponse updateStatus(@PathVariable Long id,
                                     @Valid @RequestBody UpdateTaskStatusRequest request) {
        return taskService.updateStatus(id, request.getStatus());
    }

    // 5. Удалить задачу (DELETE /api/tasks/{id}) - возвращает 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}