package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	public ResponseEntity<String> addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
		return new ResponseEntity<String>("Student created successfully", HttpStatus.OK);
	}

	public ResponseEntity<String> deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new IllegalStateException("student with id " + studentId + " does not exists");
		}
		studentRepository.deleteById(studentId);
		return new ResponseEntity<String>("Student deleted successfully", HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<String> updateStudent(Long studentId, Student student) {
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
		return new ResponseEntity<String>("Student updated successfully", HttpStatus.OK);
	}

	public boolean checkIsUserByEmail(String email) {
		System.out.println(email);
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
		if (studentOptional.isPresent()) {
			return true;
		} else {
			return false;
		}

	}
}
