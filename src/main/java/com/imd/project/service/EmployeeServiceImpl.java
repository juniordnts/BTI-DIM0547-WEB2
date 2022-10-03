package com.imd.project.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imd.project.model.Employee;
import com.imd.project.repository.EmployeeRepository;

@Component
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository modelEntityRepository;

  @Override
  public Employee save(Employee modelInstance) {
    return modelEntityRepository.save(modelInstance);
  }

  @Override
  public void delete(Employee modelInstance) {
    modelEntityRepository.delete(modelInstance);
  }

  @Override
  public Employee getOneById(Integer id) {
    return modelEntityRepository.findById(id).map(modelInstance -> {
      return modelInstance;
    }).orElseThrow(() -> null);
  }

  @Override
  public List<Employee> getAll() {
    return modelEntityRepository.findAll();
  }

}
