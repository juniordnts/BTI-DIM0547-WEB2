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

import com.imd.project.model.Appointment;
import com.imd.project.service.AppointmentService;
import com.imd.project.service.CustomerService;
import com.imd.project.service.EmployeeService;
import com.imd.project.service.ProcedureService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

  @Autowired
  @Qualifier("appointmentServiceImpl")
  AppointmentService currentModelService;

  @Autowired
  @Qualifier("customerServiceImpl")
  CustomerService customerModelService;

  @Autowired
  @Qualifier("employeeServiceImpl")
  EmployeeService employeeModelService;

  @Autowired
  @Qualifier("procedureServiceImpl")
  ProcedureService procedureModelService;

  @ModelAttribute("controller")
  public String getVersion() {
    return "appointment";
  }

  @ModelAttribute("controllerTitle")
  public String getControllerTitle() {
    return "Appointment";
  }

  @RequestMapping("/form")
  public String getForm(Model model) {
    model.addAttribute("modelInstance", new Appointment());

    model.addAttribute("customerInstances", customerModelService.getAll());
    model.addAttribute("employeeInstances", employeeModelService.getAll());
    model.addAttribute("procedureInstances", procedureModelService.getAll());
    return "appointment/form";
  }

  @RequestMapping("/form/{id}")
  public String getEdit(@PathVariable Integer id, Model model) {
    Appointment modelInstance = currentModelService.getOneById(id);
    model.addAttribute("modelInstance", modelInstance);

    model.addAttribute("customerInstances", customerModelService.getAll());
    model.addAttribute("employeeInstances", employeeModelService.getAll());
    model.addAttribute("procedureInstances", procedureModelService.getAll());
    return "appointment/form";
  }

  @RequestMapping("/one/{id}")
  public String getById(@PathVariable Integer id, Model model) {
    Appointment modelInstance = currentModelService.getOneById(id);
    model.addAttribute("modelInstance", modelInstance);
    return "appointment/one";
  }

  @RequestMapping("/list")
  public String getList(Model model) {
    List<Appointment> modelInstances = currentModelService.getAll();
    model.addAttribute("modelInstances", modelInstances);
    return "appointment/list";
  }

  @RequestMapping("/create")
  public RedirectView updateCreate(@ModelAttribute("modelInstance") Appointment modelInstance, Model model) {
    currentModelService.save(modelInstance);
    return new RedirectView("/appointment/list");
  }

  @RequestMapping("/delete/{id}")
  public RedirectView updateDelete(@PathVariable Integer id, Model model) {
    Appointment modelInstance = currentModelService.getOneById(id);
    currentModelService.delete(modelInstance);
    return new RedirectView("/appointment/list");
  }

}
