package com.imd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imd.project.model.Procedure;

public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {

}
