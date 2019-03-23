package com.akvone.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocationStatistics {

  private long userCount;

  private List<String> topStyles;
}
