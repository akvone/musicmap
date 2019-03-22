package com.akvone.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SINGERS", schema = "musicDB", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Singer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Basic
  @Column(name = "name", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
  private String name;

}
