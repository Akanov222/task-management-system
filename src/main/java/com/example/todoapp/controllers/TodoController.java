package com.example.todoapp.controllers;

import com.example.todoapp.domain.TodoItem;
import com.example.todoapp.repositories.TodoItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoItemRepository todoItemRepository;

    @GetMapping
    public String index(Model model) {
        List<TodoItem> allTodos = todoItemRepository.findAll();
        model.addAttribute("allTodos", allTodos);
        model.addAttribute("newTodo", new TodoItem());

        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute TodoItem todoItem) {
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") Long id) {
        todoItemRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/removeAll")
    public String removeAllItems() {
        todoItemRepository.deleteAll();
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchTodoItems(@RequestParam ("searchTerm") String searchTerm, Model model) {
        List<TodoItem> allTodos = todoItemRepository.findAll();
        List<TodoItem> searchResults = todoItemRepository.findByTitleContainingIgnoreCase(searchTerm);

        model.addAttribute("allTodos", searchResults);
        model.addAttribute("newTodo", new TodoItem());

        return "index";
    }
}
