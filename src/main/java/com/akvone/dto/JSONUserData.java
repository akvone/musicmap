package com.akvone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JSONUserData {

  @JsonProperty("userID")
  private String vkIdLine;

  @JsonProperty("x")
  private Float x;

  @JsonProperty("y")
  private Float y;

  @JsonProperty("locationID")
  private Long locationId;

  @JsonProperty("audios")
  private List<JSONSong> songs;

}
