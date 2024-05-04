package com.rest.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rest.demo.entity.Student;
import com.rest.demo.entity.Task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class TaskDaoImpl implements TaskDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void saveTask(Task task) {
	
		entityManager.persist(task);
	}
	
	
	public List<Task> getTasks() {
		TypedQuery<Task> theQuery = entityManager.createQuery("from Task", Task.class);
		List<Task> taskList = theQuery.getResultList();
		return taskList;
	}
}
