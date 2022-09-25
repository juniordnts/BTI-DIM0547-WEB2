package com.jeanlima.mvcapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.jeanlima.mvcapp.model.Curso;
import com.jeanlima.mvcapp.model.Estudante;
import com.jeanlima.mvcapp.service.CursoService;
import com.jeanlima.mvcapp.service.EstudanteService;

@Controller
@RequestMapping("/estudante")
public class EstudanteController {

  @Autowired
  @Qualifier("estudanteServiceImpl")
  EstudanteService estudanteService;

  @Autowired
  @Qualifier("cursoServiceImpl")
  CursoService cursoService;

  @RequestMapping("/showForm")
  public String showFormEstudante(Model model) {
    model.addAttribute("estudante", new Estudante());
    model.addAttribute("cursos", cursoService.getListaCursos());
    return "estudante/formEstudante";
  }

  @RequestMapping("/addEstudante")
  public RedirectView showFormEstudante(@ModelAttribute("estudante") Estudante estudante, Model model) {
    estudanteService.salvarEstudante(estudante);
    return new RedirectView("/estudante/getListaEstudantes");
  }

  @RequestMapping("/getListaEstudantes")
  public String showListaEstudante(Model model) {

    List<Estudante> estudantes = estudanteService.getListaEstudante();
    model.addAttribute("estudantes", estudantes);
    return "estudante/listaEstudantes";

  }

  @RequestMapping("/getEstudanteById/{id}")
  public String getEstudanteById(@PathVariable Integer id, Model model) {
    Estudante estudante = estudanteService.getEstudanteById(id);
    model.addAttribute("estudante", estudante);
    return "estudante/paginaEstudante";
  }

  @RequestMapping("/deleteEstudanteById/{id}")
  public RedirectView deleteEstudanteById(@PathVariable Integer id, Model model) {
    Estudante estudante = estudanteService.getEstudanteById(id);
    estudanteService.deletarEstudante(estudante);
    return new RedirectView("/estudante/getListaEstudantes");
  }

}
