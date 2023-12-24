package com.example.demo.student;

import com.example.demo.student.dto.StudentPostDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Student> getStudents() {
		return studentsService.getStudents();
	}

	@PostMapping
	public void registerNewStudent(@RequestBody @Valid StudentPostDto student) {
		studentsService.addNewStudent(student.getStudent());
	}

	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId) {
		studentsService.deleteStudent(studentId);
	}

	@PutMapping(path = "{studentId}")
	public void updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestBody Student student
	) {
		studentsService.updateStudent(studentId, student);
	}
}
