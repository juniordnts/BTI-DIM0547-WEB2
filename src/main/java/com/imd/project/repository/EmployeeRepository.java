package com.imd.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.imd.project.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
