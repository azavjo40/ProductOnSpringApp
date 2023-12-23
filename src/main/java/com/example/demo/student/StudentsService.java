package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentsService {
	private final StudentRepository studentRepository;

	@Autowired
	public StudentsService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new IllegalStateException("student with id " + studentId + " does not exists");
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, Student student) {
		Student studentRes = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists"));
		String name = student.getName();
		String email = student.getEmail();
		LocalDate dob = student.getDob();

		if (name != null && !Objects.equals(studentRes.getName(), name)) {
			studentRes.setName(name);
		}

		if (email != null && !Objects.equals(studentRes.getEmail(), email)) {
			studentRes.setEmail(email);
		}

		if (dob != null && !Objects.equals(studentRes.getDob(), email)) {
			studentRes.setDob(dob);
		}
	}
}
