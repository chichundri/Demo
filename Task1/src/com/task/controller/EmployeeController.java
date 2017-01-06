package com.task.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.task.dao.EmployeeDAO;
import com.task.model.Department;
import com.task.model.Employee;
import com.task.util.DepartmentEditor;

@Controller
@SessionAttributes("emp")
// ref - addEmployeeFom
public class EmployeeController {
	@Autowired
	EmployeeDAO dao;
	private Validator validator;

	public EmployeeController() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Department.class, new DepartmentEditor());
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView anythingThanLogin() {
		return new ModelAndView("redirect:/viewAllEmployees");
	}

	@RequestMapping(value = "/viewAllEmployees", method = RequestMethod.GET)
	public ModelAndView getAllEmployees() {
		List<Employee> list = dao.getAllEmployees();
		return new ModelAndView("employeeList", "employees", list);
	}

	@RequestMapping(value = "/editEmpDetails/{id}", method = RequestMethod.GET)
	public ModelAndView getAllEmployeesDetailsForEdit(@PathVariable int id, Model model) {
		// System.out.println("Id ; "+id);
		ModelAndView mav = new ModelAndView("employeeEditForm");
		Employee emp = dao.getEmployeeById(id);
//		model.addAttribute("emp", new Employee());
		model.addAttribute("emp", emp);
		List<Department> depts = dao.getAllDepartments();
		mav.addObject(model);
		mav.addObject("department", depts);
		return mav;
//		return new ModelAndView("employeeEditForm", "employee", emp);
	}

	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public ModelAndView editSave(@ModelAttribute("emp") @Valid Employee emp, BindingResult result, Model model) {
		// System.out.println(emp);
		Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
		for (ConstraintViolation<Employee> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			System.out.println(message);
//			result.addError(new FieldError("emp", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
		}
		
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("employeeEditForm");
			List<Department> depts = dao.getAllDepartments();
			mav.addObject("department", depts);
			return mav;
		}
		
		boolean status = dao.updateEmployeeById(emp);
		if (status) {
			return new ModelAndView("redirect:/viewAllEmployees");
		}
		return new ModelAndView("errorpage");
	}

	@RequestMapping(value = "/addEmployeeForm", method = RequestMethod.GET)
	public ModelAndView addNewEmployee(Model model) {
		List<Department> depts = dao.getAllDepartments();
		ModelAndView mav = new ModelAndView("addNewEmployee");
		mav.addObject("emp", new Employee());
		mav.addObject("department", depts);
		return mav;
//		return new ModelAndView("addNewEmployee", "department", depts);
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public ModelAndView addEmployee(@ModelAttribute("emp") @Valid Employee emp, BindingResult result, SessionStatus status) {
		// System.out.println(emp);

		Set<ConstraintViolation<Employee>> violations = validator.validate(emp);
		for (ConstraintViolation<Employee> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			System.out.println(message);
//			result.addError(new FieldError("emp", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
		}
		
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("addNewEmployee");
			List<Department> depts = dao.getAllDepartments();
//			mav.addObject("emp", new Employee());
			mav.addObject("department", depts);
			return mav;
//			return new ModelAndView("redirect:/addEmployeeForm");
		}

		boolean flag = dao.addEmployee(emp);
		if (flag) {
			status.setComplete();
			return new ModelAndView("redirect:/viewAllEmployees");
		}

		return new ModelAndView("errorpage");
	}

	@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(@PathVariable int id, Model model) {
		boolean status = dao.deleteEmployeeById(id);
		if (status) {
			return new ModelAndView("redirect:/viewAllEmployees");
		}
		return new ModelAndView("errorpage");
	}

	@RequestMapping(value = "/editEmpDetails", method = RequestMethod.POST)
	public ModelAndView getAllEmployeesDetailsForEdit1(@RequestParam("selectedEmpId") String idStr, Model model) {
		int id = Integer.parseInt(idStr);
		// System.out.println("Id : "+id);
		Employee emp = dao.getEmployeeById(id);
		model.addAttribute("empModel", new Employee());
		return new ModelAndView("employeeEditForm", "employee", emp);
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
	public ModelAndView deleteEmployee1(@RequestParam("selectedEmpId") String idStr, Model model) {
		// System.out.println(idStr);
		String[] words = idStr.split(",");
		List<Integer> ids = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			ids.add(Integer.parseInt(words[i]));
		}
		boolean status = dao.deleteEmployeeByIds(ids);
		if (status) {
			return new ModelAndView("redirect:/viewAllEmployees");
		}
		return new ModelAndView("errorpage");
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchEmployee(@RequestParam("searchCriteria") String searchCriteria,
	      @RequestParam("searchdropdown") String searchBy, Model model) {
		int empId = 0;
		// System.out.println(searchBy);
		// System.out.println(searchCriteria);
		if (searchBy.equals("byEmpId")) {
			empId = Integer.parseInt(searchCriteria);
			List<Employee> list = dao.searchByEmpId(empId);
			if (null != list && !list.isEmpty()) {
				return new ModelAndView("employeeList", "employees", list);
			}
			return new ModelAndView("norecordfound");
		}
		List<Employee> list = dao.searchByCriteria(searchCriteria);
		if (null != list && !list.isEmpty()) {
			return new ModelAndView("employeeList", "employees", list);
		}
		return new ModelAndView("norecordfound");
	}

	@RequestMapping(value = "/search1", method = RequestMethod.POST)
	public ModelAndView searchEmployeeByNameEmail(@RequestParam("firstName") String firstName,
	      @RequestParam("lastName") String lastName, @RequestParam("email") String email,
	      final RedirectAttributes redirectAttributes) {

		List<Employee> list = dao.searchEmployeeByNameEmail(firstName, lastName, email);
		if (null != list && !list.isEmpty()) {
			return new ModelAndView("employeeList", "employees", list);
		} else {
			redirectAttributes.addFlashAttribute("message", "No result Found.");
			return new ModelAndView("redirect:/viewAllEmployees");
		}
	}

}
