package com.akvone.entity;

import java.io.Serializable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class HistoryRecordPK implements Serializable {

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "song_id", referencedColumnName = "id", nullable = false)
  private Song song;

  public HistoryRecordPK(User user, Song song) {
    this.user = user;
    this.song = song;
  }

}
