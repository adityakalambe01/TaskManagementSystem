package com.gokapture.service;

import com.gokapture.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    Task save(Task task);

    Task findById(int id);

    List<Task> findAll(Pageable pageable);

    boolean existsById(int id);

    boolean deleteById(int id);

    Task update(int taskId, Task updatedTask);

    List<Task> findAllByStatus(String status, Pageable pageable);

    List<Task> findAllByPriority(String priority, Pageable pageable);

    List<Task> findByTitleContaining(String title, Pageable pageable);

    List<Task> findByByDescriptionContaining(String description, Pageable pageable);

    List<Task> findByDue_dateLessThanEqual(LocalDate due_date, Pageable pageable);
}
