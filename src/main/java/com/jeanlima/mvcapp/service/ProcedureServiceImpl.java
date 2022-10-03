package com.jeanlima.mvcapp.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jeanlima.mvcapp.model.Procedure;
import com.jeanlima.mvcapp.repository.ProcedureRepository;

@Component
public class ProcedureServiceImpl implements ProcedureService {

  @Autowired
  ProcedureRepository modelEntityRepository;

  @Override
  public Procedure save(Procedure modelInstance) {
    return modelEntityRepository.save(modelInstance);
  }

  @Override
  public void delete(Procedure modelInstance) {
    modelEntityRepository.delete(modelInstance);
  }

  @Override
  public Procedure getOneById(Integer id) {
    return modelEntityRepository.findById(id).map(modelInstance -> {
      return modelInstance;
    }).orElseThrow(() -> null);
  }

  @Override
  public List<Procedure> getAll() {
    return modelEntityRepository.findAll();
  }

}
