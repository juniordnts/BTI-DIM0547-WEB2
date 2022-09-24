package com.jeanlima.mvcapp.service;

import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jeanlima.mvcapp.model.Estudante;

@Component
public class EstudanteServiceImpl implements EstudanteService {

  @Autowired
  @Qualifier("cursoServiceImpl")
  CursoService cursoService;

  public int idIterator = 1;
  // public List<Estudante> estudantes = new LinkedList<Estudante>(Arrays.asList(
  // new Estudante(1, "Jackson", "D",cursoService.getCursoById(1),"Java",""),
  // new Estudante(2, "Felipe", "A",cursoService.getCursoById(1),"Java",""),
  // new Estudante(3, "Tamara", "C",cursoService.getCursoById(1),"Python","")
  // ));

  public List<Estudante> estudantes = new ArrayList<Estudante>();

  @Override
  public void salvarEstudante(Estudante estudante) {
    estudante.setId(this.idIterator);
    System.out.println(estudante.toString());
    try {
      this.estudantes.add(estudante);
      this.idIterator = this.idIterator + 1;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.toString());
    }
  }

  @Override
  public void deletarEstudante(Estudante estudante) {
    this.estudantes.remove(estudante);
  }

  @Override
  public Estudante getEstudanteById(Integer id) {
    for (Estudante estudante : estudantes) {
      if (estudante.getId() == id) {
        return estudante;
      }
    }
    return null;
  }

  @Override
  public List<Estudante> getEstudantesByCurso(int curso) {
    List<Estudante> encontrados = new ArrayList<>();
    ;
    for (Estudante estudante : estudantes) {
      if (estudante.getCurso().getId() == curso) {
        encontrados.add(estudante);
      }
    }
    return encontrados;
  }

  @Override
  public List<Estudante> getEstudantesByLinguagem(String linguagem) {
    List<Estudante> encontrados = new ArrayList<>();
    ;
    for (Estudante estudante : estudantes) {
      if (estudante.getLinguagem().equals(linguagem)) {
        encontrados.add(estudante);
      }
    }
    return encontrados;
  }

  @Override
  public List<Estudante> getListaEstudante() {
    return this.estudantes;
  }

}
