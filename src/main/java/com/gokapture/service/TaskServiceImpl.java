package com.gokapture.service;

import com.gokapture.entity.Task;
import com.gokapture.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        if (task.getId()==0){
            task.setCreated_at(LocalDate.now());
        }
        return taskRepository.save(task);
    }

    @Override
    public Task findById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable).getContent();
    }

    @Override
    public boolean existsById(int id) {
        return taskRepository.existsById(id);
    }

    @Override
    public boolean deleteById(int id) {
        if (existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Task update(int taskId, Task updatedTask) {
        if (existsById(taskId)){
            Task task = taskRepository.findById(taskId);
            if (!updatedTask.getTitle().isEmpty()){
                task.setTitle(updatedTask.getTitle());
            }
            if (!updatedTask.getDescription().isEmpty()){
                task.setDescription(updatedTask.getDescription());
            }
            if (!updatedTask.getStatus().isEmpty()){
                task.setStatus(updatedTask.getStatus());
            }
            if (!updatedTask.getPriority().isEmpty()){
                task.setPriority(updatedTask.getPriority());
            }
            if (updatedTask.getDue_date() != null){
                task.setDue_date(updatedTask.getDue_date());
            }
//            if (updatedTask.getCreated_at() != null){
//                task.setCreated_at(updatedTask.getCreated_at());
//            }
            task.setUpdated_at(LocalDate.now());
            return taskRepository.save(task);
        }
        return null;
    }

    @Override
    public List<Task> findAllByStatus(String status, Pageable pageable) {
        return taskRepository.findAllByStatus(status, pageable).getContent();
    }

    @Override
    public List<Task> findAllByPriority(String priority, Pageable pageable) {
        return taskRepository.findAllByPriority(priority, pageable).getContent();
    }

    @Override
    public List<Task> findByTitleContaining(String title, Pageable pageable) {
        System.out.println(
                title
        );
        return taskRepository.findByTitleContaining(title, pageable).getContent();
    }

    @Override
    public List<Task> findByByDescriptionContaining(String description, Pageable pageable) {
        return taskRepository.findByByDescriptionContaining(description, pageable).getContent();
    }

    @Override
    public List<Task> findByDue_dateLessThanEqual(LocalDate due_date, Pageable pageable) {
        return taskRepository.findByDue_dateLessThanEqual(due_date, pageable).getContent();
    }
}
