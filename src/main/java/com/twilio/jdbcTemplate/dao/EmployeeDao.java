package com.twilio.jdbcTemplate.dao;

import com.twilio.jdbcTemplate.model.Employee;
import com.twilio.jdbcTemplate.model.EmployeeRowMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public EmployeeDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Employee> findAll() {
    String sql = "SELECT id, first_name,last_name,email FROM employee LIMIT 100";
    return jdbcTemplate.query(sql, new EmployeeRowMapper());
  }

  public Optional<Employee> findById(int id) {
    String sql = "SELECT id, first_name, last_name, email FROM employee WHERE id = ?";
    return jdbcTemplate.query(sql,new EmployeeRowMapper(),id)
        .stream()
        .findFirst();
  }

  public int addEmployee(Employee employee) {
    String sql = "INSERT into employee(first_name,last_name,email) VALUES (?,?,?)";
    return jdbcTemplate.update(sql, employee.getFirstName(),
        employee.getLastName(),
        employee.getEmail());
  }

  public int updateEmployee(int id, Employee employee) {
    String sql = "UPDATE employee SET  first_name = ?, last_name = ?, email = ? WHERE id = ?";
    return jdbcTemplate.update(sql,
        employee.getFirstName(),
        employee.getLastName(),
        employee.getEmail(),id);
  }

  public int deleteEmployee(int id) {
    String sql = "DELETE FROM employee WHERE id = ?";
    return jdbcTemplate.update(sql,id);
  }
}
