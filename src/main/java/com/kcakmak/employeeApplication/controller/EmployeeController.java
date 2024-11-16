package com.kcakmak.employeeApplication.controller;

import com.kcakmak.employeeApplication.entity.Employee;
import com.kcakmak.employeeApplication.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get the employees from db
        List<Employee> theEmployee = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployee);

        return "employees/list";
    }

    @GetMapping("/addNewEmployee")
    public String addNewEmployee(Model theModel) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        // add to the spring model
        theModel.addAttribute("employee", theEmployee);

        return "employees/addNewEmployee";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/updateEmployee")
    public String updateEmployee(@RequestParam("employeeId") int theId, Model theModel) {

        // get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        // set employee in the model to pre-populate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to our form
        return "/employees/updateEmployee";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        // delete the employee
        employeeService.deleteById(theId);

        // redirect to the /employees/list
        return "redirect:/employees/list";
    }
}