package com.rest.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.entity.Task;
import com.rest.demo.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/save")
	public void saveTask(@RequestBody Task task) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*"); // All
		this.taskService.saveTask(task);
	}
	
	@GetMapping("/getTasks")
	public List<Task> getTask() {
		return this.taskService.getTasks();
	}
}
