package com.mooo.pashkin.task_manager.service;

import com.mooo.pashkin.task_manager.dto.CreateTaskRequest;
import com.mooo.pashkin.task_manager.dto.TaskResponse;
import com.mooo.pashkin.task_manager.entity.Task;
import com.mooo.pashkin.task_manager.entity.TaskStatus;
import com.mooo.pashkin.task_manager.exception.TaskNotFoundException;
import com.mooo.pashkin.task_manager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    // 1. Создание задачи
    @Transactional
    public TaskResponse create(CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        // Статус и createdAt установятся автоматически в @PrePersist

        Task savedTask = taskRepository.save(task);
        return toResponse(savedTask);
    }

    // 2. Получение всех задач
    @Transactional(readOnly = true)
    public List<TaskResponse> findAll() {
        return taskRepository.findAll().stream()
                .map(this::toResponse) // Превращаем каждую Task в TaskResponse
                .toList();
    }

    // 3. Получение одной задачи по ID
    @Transactional(readOnly = true)
    public TaskResponse findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return toResponse(task);
    }

    // 4. Изменение статуса
    @Transactional
    public TaskResponse updateStatus(Long id, TaskStatus status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setStatus(status);
        // При изменении @PrePersist не срабатывает, но нам это и не нужно
        return toResponse(task);
    }

    // 5. Удаление задачи
    @Transactional
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    // Вспомогательный метод: превращает сущность БД в DTO для ответа
    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt()
        );
    }
}