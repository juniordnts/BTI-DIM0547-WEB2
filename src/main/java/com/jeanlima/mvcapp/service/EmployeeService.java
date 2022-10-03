package com.jeanlima.mvcapp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jeanlima.mvcapp.model.Employee;

@Service
public interface EmployeeService {
  public Employee save(Employee curso);

  public void delete(Employee curso);

  public Employee getOneById(Integer id);

  public List<Employee> getAll();
}
