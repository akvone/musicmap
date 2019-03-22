package com.akvone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JSONSong {

  @JsonProperty("aid")
  private Long id;

  @JsonProperty("artist")
  private String singer;

  @JsonProperty("genre")
  private String styleName;

}
