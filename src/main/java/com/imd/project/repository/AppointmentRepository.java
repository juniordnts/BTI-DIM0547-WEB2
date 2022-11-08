package com.imd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imd.project.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
