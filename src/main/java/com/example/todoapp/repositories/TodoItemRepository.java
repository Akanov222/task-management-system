package com.example.todoapp.repositories;

import com.example.todoapp.domain.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
//    Long id(Long id);
    List<TodoItem> findByTitleContainingIgnoreCase(String title);
}
