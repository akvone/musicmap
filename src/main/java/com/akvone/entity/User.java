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
@Table(name = "USERS", schema = "musicDB", catalog = "")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Basic
  @Column(name = "vk_id", nullable = false, unique = true)
  private Long vkId;

  @Basic
  @Column(name = "xcoord")
  private float x;

  @Basic
  @Column(name = "ycoord")
  private float y;

}
