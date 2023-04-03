package test.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import test.api.models.Task;
import test.api.repositories.TaskRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public Task postTask(@RequestBody Map<String, Object> body) {
        String text = body.get("text").toString();
        String day = body.get("day").toString();
        Boolean reminder = (Boolean) body.get("reminder");

        return taskRepository.save(new Task(text, day, reminder));
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @PutMapping("/{id}")
    public Optional<Task> updateTask(@PathVariable String id, @RequestBody Map<String, Object> body) {
        String text = body.get("text").toString();
        String day = body.get("day").toString();
        Boolean reminder = (Boolean) body.get("reminder");

        Optional<Task> foundTask = taskRepository.findById(id);
        foundTask.ifPresent(task -> {
            if (text instanceof String) task.setText(text);
            if (day instanceof String) task.setDay(day);
            if (reminder instanceof Boolean) task.setReminder(reminder);

            taskRepository.save(task);
        });

        if (foundTask.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return foundTask;
    }

    @DeleteMapping("/{id}")
    public Optional<Task> deleteTask(@PathVariable String id) {
        Optional<Task> foundTask = taskRepository.findById(id);
        foundTask.ifPresent(task -> taskRepository.delete(task));

        if (foundTask.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return foundTask;
    }
}
