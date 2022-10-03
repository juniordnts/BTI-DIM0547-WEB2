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

import com.imd.project.model.Procedure;
import com.imd.project.service.ProcedureService;

@Controller
@RequestMapping("/procedure")
public class ProcedureController {

  @Autowired
  @Qualifier("procedureServiceImpl")
  ProcedureService currentModelService;

  @ModelAttribute("controller")
  public String getVersion() {
    return "procedure";
  }

  @ModelAttribute("controllerTitle")
  public String getControllerTitle() {
    return "Procedure";
  }

  @RequestMapping("/form")
  public String getForm(Model model) {
    model.addAttribute("modelInstance", new Procedure());
    return "procedure/form";
  }

  @RequestMapping("/form/{id}")
  public String getEdit(@PathVariable Integer id, Model model) {
    Procedure modelInstance = currentModelService.getOneById(id);
    model.addAttribute("modelInstance", modelInstance);
    return "procedure/form";
  }

  @RequestMapping("/one/{id}")
  public String getById(@PathVariable Integer id, Model model) {
    Procedure modelInstance = currentModelService.getOneById(id);
    model.addAttribute("modelInstance", modelInstance);
    return "procedure/one";
  }

  @RequestMapping("/list")
  public String getList(Model model) {
    List<Procedure> modelInstances = currentModelService.getAll();
    model.addAttribute("modelInstances", modelInstances);
    return "procedure/list";
  }

  @RequestMapping("/create")
  public RedirectView updateCreate(@ModelAttribute("modelInstance") Procedure modelInstance, Model model) {
    currentModelService.save(modelInstance);
    return new RedirectView("/procedure/list");
  }

  @RequestMapping("/delete/{id}")
  public RedirectView updateDelete(@PathVariable Integer id, Model model) {
    Procedure modelInstance = currentModelService.getOneById(id);
    currentModelService.delete(modelInstance);
    return new RedirectView("/procedure/list");
  }

}
