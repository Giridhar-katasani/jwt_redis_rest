package com.rest.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.demo.dao.TaskDao;
import com.rest.demo.entity.*;

@Service
public class TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	public void saveTask(Task task) {
		taskDao.saveTask(task);
	}
	
	public List<Task> getTasks() {
		return taskDao.getTasks();
	}
}
