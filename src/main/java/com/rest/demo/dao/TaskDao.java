package com.rest.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rest.demo.entity.*;

public interface TaskDao {
	
	public void saveTask(Task task);
	
	public List<Task> getTasks(); 

}
