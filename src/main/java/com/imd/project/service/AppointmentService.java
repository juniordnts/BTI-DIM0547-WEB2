package com.imd.project.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imd.project.model.Appointment;

@Service
public interface AppointmentService {
  public Appointment save(Appointment curso);

  public void delete(Appointment curso);

  public Appointment getOneById(Integer id);

  public List<Appointment> getAll();
}
