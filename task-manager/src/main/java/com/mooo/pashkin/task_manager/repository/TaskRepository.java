package com.mooo.pashkin.task_manager.repository;

import com.mooo.pashkin.task_manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}