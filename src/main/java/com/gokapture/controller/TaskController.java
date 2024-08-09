package com.gokapture.controller;

import com.gokapture.entity.Task;
import com.gokapture.entity.User;
import com.gokapture.service.TaskService;
import com.gokapture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        if (task.getUser() == null) {
            task.setUser(
                    userService.findByUsername(
                            (String) UserController.httpSession.getAttribute("username")
                    )
            );
        }
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

    @GetMapping(value = "/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(
            @RequestParam(value = "page", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize,
            @PathVariable("status") String status) {
        return ResponseEntity.ok(
                taskService.findAllByStatus(
                        status, PageRequest.of(pageNumber,pageSize)
                )
        );
    }

    @GetMapping(value = "/priority/{priority}")
    public ResponseEntity<List<Task>> findAllByPriority(
            @RequestParam(value = "page", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize,
            @PathVariable("priority") String priority) {
        return ResponseEntity.ok(
                taskService.findAllByPriority(
                        priority,
                        PageRequest.of(pageNumber,pageSize)
                )
        );
    }

    @GetMapping(value = "/search-task-by")
    public ResponseEntity<List<Task>> searchByTitle(
            @RequestParam(value = "page", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "size", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "due_date", required = false) String due_date
            ) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<Task> taskList = null;
        if (title!=null){
            taskList = taskService.findByTitleContaining(title, pageable);
        }else if (description!=null){
            taskList = taskService.findByByDescriptionContaining(description, pageable);
        }else if (due_date != null){
            taskList = taskService.findByDue_dateLessThanEqual(LocalDate.parse(due_date), pageable);
        }

        return ResponseEntity.ok(taskList);
    }


}
