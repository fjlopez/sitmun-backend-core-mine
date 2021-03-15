package org.sitmun.plugin.core.domain;


import lombok.*;
import org.sitmun.plugin.core.constraints.CodeList;
import org.sitmun.plugin.core.constraints.CodeLists;
import org.sitmun.plugin.core.constraints.HttpURL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

import static org.sitmun.plugin.core.domain.Constants.IDENTIFIER;
import static org.sitmun.plugin.core.domain.Constants.URL;

/**
 * Territorial entity.
 */
@Entity
@Table(name = "STM_TERRITORY", uniqueConstraints = {
  @UniqueConstraint(name = "STM_TER_NOM_UK", columnNames = {"TER_NAME"})})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Territory {

  /**
   * Unique identifier.
   */
  @TableGenerator(
    name = "STM_TERRITORY_GEN",
    table = "STM_SEQUENCE",
    pkColumnName = "SEQ_NAME",
    valueColumnName = "SEQ_COUNT",
    pkColumnValue = "TER_ID",
    allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "STM_TERRITORY_GEN")
  @Column(name = "TER_ID")
  private Integer id;

  /**
   * Geographic code.
   */
  @Column(name = "TER_CODMUN", length = IDENTIFIER)
  @NotBlank
  private String code;

  /**
   * Territory name.
   */
  @Column(name = "TER_NAME", length = 250)
  @NotBlank
  private String name;

  /**
   * Territorial authority name.
   */
  @Column(name = "TER_ADMNAME", length = 250)
  private String territorialAuthorityName;

  /**
   * Territorial authority address.
   */
  @Column(name = "TER_ADDRESS", length = 250)
  private String territorialAuthorityAddress;

  /**
   * Territorial authority email.
   */
  @Column(name = "TER_EMAIL", length = 250)
  @Email
  private String territorialAuthorityEmail;

  /**
   * Territory scope.
   */
  @Column(name = "TER_SCOPE", length = IDENTIFIER)
  @CodeList(CodeLists.TERRITORY_SCOPE)
  private String scope;

  /**
   * Link to the territorial authority logo.
   */
  @Column(name = "TER_LOGO", length = URL)
  @HttpURL
  private String territorialAuthorityLogo;

  /**
   * Bounding box of the territory.
   */
  @Column(name = "TER_EXTENT", length = 250)
  private String extent;

  /**
   * <code>true</code> if the territory is blocked.
   */
  @Column(name = "TER_BLOCKED")
  @NotNull
  private Boolean blocked;

  /**
   * Territory typology.
   */
  @ManyToOne
  @JoinColumn(name = "TER_TYPID", foreignKey = @ForeignKey(name = "STM_TER_FK_TGR"))
  private TerritoryType type;

  /**
   * Notes.
   */
  @Column(name = "TER_NOTE", length = 250)
  private String note;

  /**
   * Creation date.
   */
  @Column(name = "TER_CREATED")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  /**
   * Territory typology.
   */
  @ManyToOne
  @JoinColumn(name = "TER_GTYPID", foreignKey = @ForeignKey(name = "STM_TER_FK_TET"))
  private TerritoryGroupType groupType;

  /**
   * Territories that are part of this territory.
   */
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinTable(
    name = "STM_GRP_TER",
    joinColumns = @JoinColumn(
      name = "GTE_TERID",
      foreignKey = @ForeignKey(name = "STM_GRT_FK_TER")),
    inverseJoinColumns = @JoinColumn(
      name = "GTE_TERMID",
      foreignKey = @ForeignKey(name = "STM_GRT_FK_TEM")))
  private Set<Territory> members;

  /**
   * Territories of which this territory is part of.
   */
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinTable(
    name = "STM_GRP_TER",
    joinColumns = @JoinColumn(
      name = "GTE_TERMID",
      foreignKey = @ForeignKey(name = "STM_GRT_FK_TERM")),
    inverseJoinColumns = @JoinColumn(
      name = "GTE_TERID",
      foreignKey = @ForeignKey(name = "STM_GRT_FK_TER")))
  private Set<Territory> memberOf;

  /**
   * Task availabilities.
   */
  @OneToMany(mappedBy = "territory", cascade = CascadeType.ALL)
  private Set<TaskAvailability> taskAvailabilities;

  /**
   * Cartography availabilities.
   */
  @OneToMany(mappedBy = "territory", cascade = CascadeType.ALL)
  private Set<CartographyAvailability> cartographyAvailabilities;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof Territory))
      return false;

    Territory other = (Territory) o;

    return id != null &&
      id.equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
