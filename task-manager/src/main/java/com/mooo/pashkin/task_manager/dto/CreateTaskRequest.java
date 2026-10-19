package com.mooo.pashkin.task_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest {

    @NotBlank(message = "Название задачи обязательно")
    @Size(min = 3, max = 100, message = "Название должно содержать от 3 до 100 символов")
    private String title;

    @Size(max = 500, message = "Описание не должно превышать 500 символов")
    private String description;
}