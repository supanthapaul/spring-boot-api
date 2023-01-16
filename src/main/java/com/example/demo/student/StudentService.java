package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}

		studentRepository.save(student);
	}

	public void deleteStudent(Long id) {
		boolean exists = studentRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Student with that ID doesn't exist!");
		}

		studentRepository.deleteById(id);
	}

	@Transactional
	public void updateStudent(Long id, String name, String email) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Student with that ID doesn't exist!"));

		if (name != null && name.length() > 0 && !student.getName().equals(name)) {
			student.setName(name);
		}

		if (email != null && email.length() > 0 && !student.getEmail().equals(email)) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if (studentOptional.isPresent()) {
				throw new IllegalStateException("Email taken");
			}
			
			student.setEmail(email);
		}
	}
}
