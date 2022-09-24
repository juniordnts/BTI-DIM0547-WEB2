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
  public String showFormEstudante(@ModelAttribute("estudante") Estudante estudante, Model model) {
    int cursoId = Integer.parseInt(estudante.getCurso().getNome());
    Curso cursoObj = cursoService.getCursoById(cursoId);
    estudante.setCurso(cursoObj);
    estudanteService.salvarEstudante(estudante);
    model.addAttribute("estudante", estudante);
    return "estudante/paginaEstudante";
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

  @RequestMapping("/getEstudanteByCurso")
  public String getEstudanteByCurso(@RequestParam int id, Model model) {
    List<Estudante> estudantes = estudanteService.getEstudantesByCurso(id);
    Curso cursoObject = cursoService.getCursoById(id);
    model.addAttribute("estudantes", estudantes);
    model.addAttribute("tipo", "Curso");
    List<Curso> cursoNomes = new ArrayList<Curso>();
    for (Curso curso : cursoService.getListaCursos()) {
      if (curso.getId() == id) {
        cursoNomes.add(curso);
      }
    }
    model.addAttribute("tipos", cursoService.getListaCursos());
    model.addAttribute("valor", cursoObject.getNome());
    return "estudante/listaEstudantesPor";
  }

  @RequestMapping("/getEstudanteByLinguagem")
  public String getEstudanteByLinguagem(@RequestParam String id, Model model) {
    List<Estudante> estudantes = estudanteService.getEstudantesByLinguagem(id);
    model.addAttribute("estudantes", estudantes);
    model.addAttribute("tipo", "Linguagem");
    model.addAttribute("tipos", Arrays.asList("Java", "C", "Python", "Javascript"));
    model.addAttribute("valor", id);
    return "estudante/listaEstudantesPor";
  }

  @RequestMapping("/deleteEstudanteById/{id}")
  public RedirectView deleteEstudanteById(@PathVariable Integer id, Model model) {
    Estudante estudante = estudanteService.getEstudanteById(id);
    estudanteService.deletarEstudante(estudante);
    return new RedirectView("/estudante/getListaEstudantes");
  }

}
