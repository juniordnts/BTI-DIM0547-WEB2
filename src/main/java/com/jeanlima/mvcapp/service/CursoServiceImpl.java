package com.jeanlima.mvcapp.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.jeanlima.mvcapp.model.Curso;

@Component
public class CursoServiceImpl implements CursoService {

  public int idIterator = 4;
  public List<Curso> cursos = new LinkedList<Curso>(
      Arrays.asList(
          new Curso(1, "BTI"),
          new Curso(2, "Eng. Computação"),
          new Curso(3, "Eng. Software")));

  @Override
  public void salvarCurso(Curso curso) {
    curso.setId(this.idIterator);
    this.cursos.add(curso);
    this.idIterator = this.idIterator + 1;
  }

  @Override
  public void deletarCurso(Curso curso) {
    this.cursos.remove(curso);
  }

  @Override
  public Curso getCursoById(Integer id) {
    for (Curso curso : cursos) {
      if (curso.getId() == id) {
        return curso;
      }
    }
    return null;
  }

  @Override
  public List<Curso> getListaCursos() {
    return this.cursos;
  }

}
