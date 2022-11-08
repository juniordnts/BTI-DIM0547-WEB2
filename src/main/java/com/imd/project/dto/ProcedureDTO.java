package com.imd.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureDTO {
  @NonNull
  private Float price;
  @NonNull
  private String name;
}
