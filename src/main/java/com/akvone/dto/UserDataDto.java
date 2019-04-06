package com.akvone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDto implements Serializable {

  @JsonProperty("userID")
  @NonNull
  private Long vkId;

  @JsonProperty("coords")
  private double[] coordinates;

  @JsonProperty("locationID")
  private Long locationId;

  @JsonProperty("groups")
  private List<GroupDto> groupDtos;

}
