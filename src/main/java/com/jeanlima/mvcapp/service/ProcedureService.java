package com.jeanlima.mvcapp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jeanlima.mvcapp.model.Procedure;

@Service
public interface ProcedureService {
  public Procedure save(Procedure curso);

  public void delete(Procedure curso);

  public Procedure getOneById(Integer id);

  public List<Procedure> getAll();
}
