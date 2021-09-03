package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/tasks")
    public void create(@RequestBody Task task) {
         taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public List<Task> getTasks () {
        List<Task> task1 = new ArrayList<Task>();
        taskRepository.findAll().forEach(x->task1.add(x));
        return task1;
    }

    @GetMapping("/tasks/{taskid}")
    public Task getTask (@PathVariable("taskid") Long taskid) {
        return taskRepository.findById(taskid).get();
    }

    @PutMapping ("/tasks/{taskid}")
    public Task taskUpdate(@PathVariable("taskid") Long taskid,@RequestBody Task task ){
        task.setId(taskid);
        return taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{taskid}")
    public void delTask (@PathVariable("taskid") Long taskid ){
        taskRepository.deleteById(taskid);
    }

    @PatchMapping("/tasks/{taskid}")
    public  void patchMethod (@PathVariable("taskid") Long taskid,@RequestBody Task task ){
        if (task.isDone()){
            taskRepository.markAsDone(taskid);
        }

    }

    @PatchMapping("/tasks/{taskid}:mark-as-done")
    public  void patchMethod (@PathVariable Long id  ){
        taskRepository.markAsDone(id);


    }
}
