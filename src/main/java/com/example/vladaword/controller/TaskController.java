package com.example.vladaword.controller;

import com.example.vladaword.model.Task;
import com.example.vladaword.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "taskFile";
    }

    @GetMapping("/new")
    public String showNewTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "newTask";  
    }

    @PostMapping
    public String addTask(@ModelAttribute Task task) {
        taskService.saveTask(task);
        return "redirect:/tasks";  // Перенаправление на список задач после добавления
    }

    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable  Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "editTask";  // Имя шаблона для редактирования задачи
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        task.setId(id);
        taskService.saveTask(task);
        return "redirect:/tasks";  // Перенаправление на список задач после обновления
    }

   @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable  Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";  // Перенаправление на список задач после удаления
    }
}
