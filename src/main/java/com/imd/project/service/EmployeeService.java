package com.imd.project.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imd.project.model.Employee;

@Service
public interface EmployeeService {
  public Employee save(Employee item);

  public void delete(Employee item);

  public Employee getOneById(Integer id);

  public List<Employee> getAll();
}
