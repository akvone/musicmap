package com.akvone.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LOCATIONS", schema = "musicDB", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Location implements Serializable {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Basic
  @Column(name = "name", nullable = false)
  private String name;

}
