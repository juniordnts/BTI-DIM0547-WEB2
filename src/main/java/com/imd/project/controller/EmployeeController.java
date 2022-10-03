package com.imd.project.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.imd.project.model.Employee;
import com.imd.project.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  @Qualifier("employeeServiceImpl")
  EmployeeService currentModelService;

  @ModelAttribute("controller")
  public String getController() {
    return "employee";
  }

  @ModelAttribute("controllerTitle")
  public String getControllerTitle() {
    return "Employee";
  }

  @RequestMapping("/form")
  public String getForm(Model model) {
    model.addAttribute("modelInstance", new Employee());
    return "employee/form";
  }

  @RequestMapping("/form/{id}")
  public String getEdit(@PathVariable Integer id, Model model) {
    Employee modelInstance = currentModelService.getOneById(id);
    model.addAttribute("modelInstance", modelInstance);
    return "employee/form";
  }

  @RequestMapping("/one/{id}")
  public String getById(@PathVariable Integer id, Model model) {
    Employee modelInstance = currentModelService.getOneById(id);
    model.addAttribute("modelInstance", modelInstance);
    return "employee/one";
  }

  @RequestMapping("/list")
  public String getList(Model model) {
    List<Employee> modelInstances = currentModelService.getAll();
    model.addAttribute("modelInstances", modelInstances);
    return "employee/list";
  }

  @RequestMapping("/create")
  public RedirectView updateCreate(@ModelAttribute("modelInstance") Employee modelInstance, Model model) {
    currentModelService.save(modelInstance);
    return new RedirectView("/employee/list");
  }

  @RequestMapping("/delete/{id}")
  public RedirectView updateDelete(@PathVariable Integer id, Model model) {
    Employee modelInstance = currentModelService.getOneById(id);
    currentModelService.delete(modelInstance);
    return new RedirectView("/employee/list");
  }

}
