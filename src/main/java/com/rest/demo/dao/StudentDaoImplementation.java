package com.rest.demo.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.demo.entity.Student;
import com.rest.demo.exceptions.StudentNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDaoImplementation implements StudentDao {

	@Autowired
	public EntityManager entityManager;

	public StudentDaoImplementation(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		TypedQuery<Student> theQuery = entityManager.createQuery("from Student", Student.class);
		List<Student> studentList = theQuery.getResultList();
		return studentList;
	}

	public void saveStudent(Student student) {
		
		Student s = new Student();
	        
        // Set the properties of the new entity using the provided student object
        s.setFirst_name(student.getFirst_name());
        s.setLast_name(student.getLast_name());
        s.setEmail(student.getEmail());
        System.out.println(s);
        
        // begin the transaction
        entityManager.getTransaction().begin();
        
        // Persist the new entity to the database
        entityManager.persist(s);
        
        //commit the transaction
        entityManager.getTransaction().commit();
	}

	public Student getStudentById(int id) throws StudentNotFoundException {
		Student student = entityManager.find(Student.class, id);
		if(Objects.isNull(student))
			throw new StudentNotFoundException("student not found in db");
		return student;
	}

	@Transactional
	public Student updateStudent(Student s, int id) {

		Student studentObj = this.getStudentById(id);

		System.out.println(studentObj);
		if (Objects.isNull(studentObj)) {
			throw new StudentNotFoundException("student id does not exist");
		}

		studentObj.setId(id);
		studentObj.setFirst_name(s.getFirst_name());
		studentObj.setLast_name(s.getLast_name());
		studentObj.setEmail(s.getEmail());
		Student managedStudent = entityManager.merge(studentObj);
		System.out.println(managedStudent);
		return managedStudent;
	}
	
	@Transactional
	public void deleteStudent(int id) {
		 Student student = entityManager.find(Student.class, id);
		 if(student == null) {
			 throw new StudentNotFoundException("student id does not exist"); 
		 }
		 entityManager.remove(student); 
	}
}
