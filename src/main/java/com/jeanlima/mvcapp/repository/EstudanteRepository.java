package com.jeanlima.mvcapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeanlima.mvcapp.model.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Integer> {

}
