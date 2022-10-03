package com.imd.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.imd.project.service.AppointmentService;
import com.imd.project.service.CustomerService;

@Controller
public class BaseController {

  @Autowired
  @Qualifier("appointmentServiceImpl")
  AppointmentService appointmentModelService;

  @Autowired
  @Qualifier("customerServiceImpl")
  CustomerService customerModelService;

  @GetMapping("/")
  public String shwoPage(Model model) {

    model.addAttribute("appointmentInstances", appointmentModelService.getAll());
    model.addAttribute("customerInstances", customerModelService.getAll());
    return "home";
  }

}
