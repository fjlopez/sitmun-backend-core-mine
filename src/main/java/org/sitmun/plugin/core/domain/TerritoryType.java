package org.sitmun.plugin.core.domain;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Type of territorial entities.
 */
@Entity
@Table(name = "STM_TER_TYP", uniqueConstraints = {
    @UniqueConstraint(name = "STM_TET_NOM_UK", columnNames = {"TET_NAME"})})
public class TerritoryType {

  /**
   * Unique identifier.
   */
  @TableGenerator(
      name = "STM_TIPOGRP_GEN",
      table = "STM_CODIGOS",
      pkColumnName = "GEN_CODIGO",
      valueColumnName = "GEN_VALOR",
      pkColumnValue = "TGR_CODIGO",
      allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "STM_TIPOGRP_GEN")
  @Column(name = "TET_ID", precision = 11)
  private BigInteger id;

  /**
   * Name.
   */
  @NotNull
  @Column(name = "TET_NAME", nullable = false, length = 250)
  private String name;

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
