package com.gokapture.controller;

import com.gokapture.entity.Task;
import com.gokapture.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.save(task));
    }

    @GetMapping(value = "/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable("taskId") int id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
            @RequestParam(value = "page", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(taskService.findAll(PageRequest.of(pageNumber,pageSize)));
    }

    @PutMapping(value = "/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable("taskId") int id, @RequestBody Task updatedTask) {
        Task task = taskService.update(id, updatedTask);

        return (task != null) ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") int id) {
        return (taskService.deleteById(id)) ? ResponseEntity.ok("Task Deleted Successfully") : ResponseEntity.notFound().build();
    }
}
