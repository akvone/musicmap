package com.akvone.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HISTORY", schema = "musicDB", catalog = "")
@IdClass(HistoryRecordPK.class)
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class HistoryRecord implements Serializable {

  public HistoryRecord(HistoryRecordPK historyRecordPK) {
    song = historyRecordPK.getSong();
    user = historyRecordPK.getUser();
  }

  @Id
  private User user;

  @ManyToOne
  @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
  private Location location;

  @Id
  private Song song;

}