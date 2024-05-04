package com.rest.demo.dao;

import java.util.List;

import java.util.Objects;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rest.demo.entity.Student;
import com.rest.demo.exceptions.StudentNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
@Transactional
public class StudentDaoImplementation {

	private EntityManager entityManager;

	public StudentDaoImplementation(EntityManager entityManager) {
		
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}

	public List<Student> getStudents() {
		
		// TODO Auto-generated method stub
		TypedQuery<Student> theQuery = entityManager.createQuery("from Student", Student.class);
		List<Student> studentList = theQuery.getResultList();
		return studentList;
	}

	@Transactional
	public Student saveStudent(Student student) {

		Student s = new Student();

		// Set the properties of the new entity using the provided student object
		s.setFirst_name(student.getFirst_name());
		s.setLast_name(student.getLast_name());
		s.setEmail(student.getEmail());

		// begin the transaction
//        entityManager.getTransaction().begin();

		// Persist the new entity to the database
		entityManager.persist(s);
		return s;

		// commit the transaction
//        entityManager.getTransaction().commit();
	}

	public Student getStudentById(int id) throws StudentNotFoundException {
		Student student = entityManager.find(Student.class, id);
		if (Objects.isNull(student))
			throw new StudentNotFoundException("student not found in db");
		return student;
	}

	@Transactional
	public Student updateStudent(Student s, int id) {

		Student studentObj = this.getStudentById(id);

		if (Objects.isNull(studentObj)) {
			throw new StudentNotFoundException("student id does not exist");
		}

		studentObj.setId(id);
		studentObj.setFirst_name(s.getFirst_name());
		studentObj.setLast_name(s.getLast_name());
		studentObj.setEmail(s.getEmail());
		Student managedStudent = entityManager.merge(studentObj);
		return managedStudent;
	}

	@Transactional
	public void deleteStudent(int id) {

		Student student = entityManager.find(Student.class, id);
		if (student == null) {
			throw new StudentNotFoundException("student id does not exist");
		}
		entityManager.remove(student);
	}
}
