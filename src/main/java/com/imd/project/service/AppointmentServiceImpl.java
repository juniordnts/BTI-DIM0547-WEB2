package com.imd.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imd.project.model.Appointment;
import com.imd.project.repository.AppointmentRepository;

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
    }).orElseGet(() -> null);
  }

  @Override
  public List<Appointment> getAll() {
    return modelEntityRepository.findAll();
  }

}
