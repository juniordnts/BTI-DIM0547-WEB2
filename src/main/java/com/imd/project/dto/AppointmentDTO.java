package com.imd.project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
  private String date;
  private Integer customer;
  private Integer employee;
  private Integer coupon;
  private List<Integer> procedures;
}
