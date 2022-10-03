package com.jeanlima.mvcapp.controller;

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

import com.jeanlima.mvcapp.model.Customer;
import com.jeanlima.mvcapp.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  @Qualifier("customerServiceImpl")
  CustomerService currentModelService;

  @ModelAttribute("controller")
  public String getVersion() {
    return "customer";
  }

  @ModelAttribute("controllerTitle")
  public String getControllerTitle() {
    return "Customer";
  }

  @RequestMapping("/form")
  public String getForm(Model model) {
    model.addAttribute("modelInstance", new Customer());
    return "customer/form";
  }

  @RequestMapping("/form/{id}")
  public String getEdit(@PathVariable Integer id, Model model) {
    Customer modelInstance = currentModelService.getOneById(id);
    model.addAttribute("modelInstance", modelInstance);
    return "customer/form";
  }

  @RequestMapping("/one/{id}")
  public String getById(@PathVariable Integer id, Model model) {
    Customer modelInstance = currentModelService.getOneById(id);
    model.addAttribute("modelInstance", modelInstance);
    return "customer/one";
  }

  @RequestMapping("/list")
  public String getList(Model model) {
    List<Customer> modelInstances = currentModelService.getAll();
    model.addAttribute("modelInstances", modelInstances);
    return "customer/list";
  }

  @RequestMapping("/create")
  public RedirectView updateCreate(@ModelAttribute("modelInstance") Customer modelInstance, Model model) {
    currentModelService.save(modelInstance);
    return new RedirectView("/customer/list");
  }

  @RequestMapping("/delete/{id}")
  public RedirectView updateDelete(@PathVariable Integer id, Model model) {
    Customer modelInstance = currentModelService.getOneById(id);
    currentModelService.delete(modelInstance);
    return new RedirectView("/customer/list");
  }

}
