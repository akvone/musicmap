package com.akvone.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocationStatisticsDto {

  private int userCount;

  private List<String> topStyles;
}
