package com.imd.project.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imd.project.model.Procedure;

@Service
public interface ProcedureService {
  public Procedure save(Procedure item);

  public void delete(Procedure item);

  public Procedure getOneById(Integer id);

  public List<Procedure> getAll();
}
