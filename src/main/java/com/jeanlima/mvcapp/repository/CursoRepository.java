package com.jeanlima.mvcapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeanlima.mvcapp.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

}
