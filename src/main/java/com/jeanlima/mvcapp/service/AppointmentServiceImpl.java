package com.jeanlima.mvcapp.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jeanlima.mvcapp.model.Appointment;
import com.jeanlima.mvcapp.repository.AppointmentRepository;

@Component
public class AppointmentServiceImpl implements AppointmentService {

  @Autowired
  AppointmentRepository modelEntityRepository;

  @Override
  public Appointment save(Appointment modelInstance) {
    return modelEntityRepository.save(modelInstance);
  }

  @Override
  public void delete(Appointment modelInstance) {
    modelEntityRepository.delete(modelInstance);
  }

  @Override
  public Appointment getOneById(Integer id) {
    return modelEntityRepository.findById(id).map(modelInstance -> {
      return modelInstance;
    }).orElseThrow(() -> null);
  }

  @Override
  public List<Appointment> getAll() {
    return modelEntityRepository.findAll();
  }

}
