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
@Table(name = "user_groups", schema = "musicDB")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class GroupEntity implements Serializable {

  @Id
  private Long id;

  @Basic
  @Column(nullable = false)
  private String name;

}
