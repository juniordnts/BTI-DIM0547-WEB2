package com.jeanlima.mvcapp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jeanlima.mvcapp.model.Curso;

@Service
public interface CursoService {
  public void salvarCurso(Curso curso);

  public void deletarCurso(Curso curso);

  public Curso getCursoById(Integer id);

  public List<Curso> getListaCursos();
}
