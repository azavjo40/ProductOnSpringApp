package com.example.demo.student;

import com.example.demo.student.dto.StudentPostDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
	private final StudentsService studentsService;

	@Autowired
	public StudentController(StudentsService studentsService) {
		this.studentsService = studentsService;
	}

	@GetMapping
	public List<Student> getStudents(@AuthenticationPrincipal Student student) {
		System.out.println(student);
		return studentsService.getStudents();
	}

	@PostMapping
	public ResponseEntity<String> registerNewStudent(@RequestBody @Valid StudentPostDto student) {
		return studentsService.addNewStudent(student.getStudent());
	}

	@DeleteMapping(path = "{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId) {
		return studentsService.deleteStudent(studentId);
	}

	@PutMapping(path = "{studentId}")
	public ResponseEntity<String> updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestBody Student student
	) {
		return studentsService.updateStudent(studentId, student);
	}
}
