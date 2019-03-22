package com.akvone.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SONGS", schema = "musicDB", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Song implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "vk_id", unique = true)
  private Long vkId;

  @ManyToOne
  @JoinColumn(name = "singer_id", referencedColumnName = "id")
  private Singer singer;

  @ManyToOne
  @JoinColumn(name = "style_id", referencedColumnName = "id")
  private Style style;

}
