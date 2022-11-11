package dmacc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.model.Student;
import dmacc.repository.StudentRepository;

@Controller
public class WebController {
	@Autowired
	StudentRepository repo;
	
	@GetMapping({"/", "viewAll"})
	public String viewAllStudents(Model model) {
		if(repo.findAll().isEmpty()) {
			return addNewStudent(model);
		}
		model.addAttribute("student", repo.findAll());
		return "students";
	}
	
	@GetMapping("/inputStudent")
	public String addNewStudent(Model model) {
		Student s = new Student();
		model.addAttribute("newStudent", s);
		return "input";
	}
	
	@PostMapping("/inputStudent")
	public String addNewStudent(@ModelAttribute Student s, Model model) {
		if (s.getFirstName() == "" || s.getLastName() == "" || s.getYear() == 0) {
			return "input";
		} else {
		repo.save(s);
		return viewAllStudents(model);
		}
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateStudent(@PathVariable("id") long id, Model model) {
		Student s = repo.findById(id).orElse(null);
		model.addAttribute("newStudent", s);
		return "input";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id") long id, Model model) {
		Student s = repo.findById(id).orElse(null);
		repo.delete(s);
		return viewAllStudents(model);
	}
	
	@PostMapping("/update/{id}")
	public String reviseStudent(Student s, Model model) {
		repo.save(s);
		return viewAllStudents(model);
	}
}
