package com.twilio.jdbcTemplate.controller;

import com.twilio.jdbcTemplate.dao.EmployeeDao;
import com.twilio.jdbcTemplate.model.Employee;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
  private final EmployeeDao employeeDao;

  @Autowired
  public EmployeeController(EmployeeDao employeeDao) {
    this.employeeDao = employeeDao;
  }

  @PostMapping("/add")
  public void addEmployee(@Valid @RequestBody Employee employee){
    employeeDao.addEmployee(employee);
  }

  @GetMapping("/all")
  public List<Employee> findAll(){
    return employeeDao.findAll();
  }

  @GetMapping("/{id}")
  public Employee findById(@PathVariable("id") int id){
    return employeeDao.findById(id)
        .orElseThrow(() -> new RuntimeException("employee not found"));
  }

  @PutMapping("/update/{id}")
  public int updateEmployee(@Valid @RequestBody Employee employee, @PathVariable("id") int id){
    return employeeDao.updateEmployee(id,employee);
  }

  @GetMapping("/delete/{id}")
  public void deleteEmployee(@PathVariable("id") int id){
    employeeDao.deleteEmployee(id);
  }
}
