package com.gokapture.repo;

import com.gokapture.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task save(Task task);

    Task findById(int id);

    Page<Task> findAll(Pageable pageable);

    boolean existsById(int id);

    void deleteById(int id);

    @Query("from Task t where t.status=?1")
    Page<Task> findAllByStatus(String status, Pageable pageable);

    @Query("from Task t where t.priority=?1")
    Page<Task> findAllByPriority(String priority, Pageable pageable);

    Page<Task> findByTitleContaining(String title, Pageable pageable);

    @Query("from Task t where t.description like %?1%")
    Page<Task> findByByDescriptionContaining(String description, Pageable pageable);

    @Query("from Task t where t.due_date <= ?1")
    Page<Task> findByDue_dateLessThanEqual(LocalDate due_date, Pageable pageable);
}
