package com.mooo.pashkin.task_manager.dto;

import com.mooo.pashkin.task_manager.entity.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskStatusRequest {

    @NotNull(message = "Статус обязателен")
    private TaskStatus status;
}