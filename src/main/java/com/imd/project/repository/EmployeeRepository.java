package com.imd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imd.project.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
