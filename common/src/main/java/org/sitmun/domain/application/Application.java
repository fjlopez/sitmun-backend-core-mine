package org.sitmun.domain.application;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.sitmun.authorization.dto.ClientConfigurationViews;
import org.sitmun.domain.CodeListsConstants;
import org.sitmun.domain.PersistenceConstants;
import org.sitmun.domain.application.background.ApplicationBackground;
import org.sitmun.domain.application.parameter.ApplicationParameter;
import org.sitmun.domain.cartography.permission.CartographyPermission;
import org.sitmun.domain.role.Role;
import org.sitmun.domain.tree.Tree;
import org.sitmun.infrastructure.persistence.type.codelist.CodeList;
import org.sitmun.infrastructure.persistence.type.list.StringListAttributeConverter;
import org.sitmun.infrastructure.persistence.type.srs.Srs;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A SITMUN application.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "STM_APP")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Application {

  /**
   * Application unique identifier.
   */
  @Id
  @Column(name = "APP_ID")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "STM_APP_GEN")
  @TableGenerator(name = "STM_APP_GEN", table = "STM_SEQUENCE", pkColumnName = "SEQ_NAME",
    valueColumnName = "SEQ_COUNT", pkColumnValue = "APP_ID", allocationSize = 1)
  @JsonView({ClientConfigurationViews.Base.class, ClientConfigurationViews.ApplicationTerritory.class})
  private Integer id;

  /**
   * Application name.
   */
  @Column(name = "APP_NAME", length = PersistenceConstants.IDENTIFIER)
  @NotBlank
  private String name;

  /**
   * Application type (external or internal).
   */
  @Column(name = "APP_TYPE", length = PersistenceConstants.IDENTIFIER)
  @NotNull
  @CodeList(CodeListsConstants.APPLICATION_TYPE)
  @JsonView({ClientConfigurationViews.ApplicationTerritory.class})
  private String type;

  /**
   * Title to be shown in the browser and in the application when it is internal.
   */
  @Column(name = "APP_TITLE", length = PersistenceConstants.SHORT_DESCRIPTION)
  @JsonView({ClientConfigurationViews.ApplicationTerritory.class})
  private String title;

  /**
   * CSS to use in this application when it is internal.
   */
  @Column(name = "APP_THEME", length = 30)
  private String theme;

  /**
   * Scales to be used in this application when it is internal.
   */
  @Column(name = "APP_SCALES", length = 250)
  @Convert(converter = StringListAttributeConverter.class)
  private List<String> scales;

  /**
   * Projection to be used in this application when it is internal.
   */
  @Column(name = "APP_PROJECT", length = PersistenceConstants.IDENTIFIER)
  @Srs
  private String srs;

  /**
   * The JSP viewer to be loaded in this application when it is internal or a link to the
   * external application.
   */
  @Column(name = "APP_TEMPLATE", length = PersistenceConstants.SHORT_DESCRIPTION)
  @NotNull
  private String jspTemplate;

  /**
   * When the application is internal {@code True} if the application refreshes automatically;
   * {@code False} if an "update map" button is required.
   */
  @Column(name = "APP_REFRESH")
  private Boolean treeAutoRefresh;

  /**
   * Can access a "parent" territory.
   */
  @Column(name = "APP_ENTRYS")
  private Boolean accessParentTerritory;

  /**
   * Can access a "children" territory.
   */
  @Column(name = "APP_ENTRYM")
  private Boolean accessChildrenTerritory;

  /**
   * Situation map when the application is internal.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "APP_GGIID", foreignKey = @ForeignKey(name = "STM_APP_FK_GGI"))
  private CartographyPermission situationMap;

  /**
   * Created date.
   */
  @Column(name = "APP_CREATED")
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdDate;

  /**
   * Application parameters.
   */
  @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private Set<ApplicationParameter> parameters = new HashSet<>();

  /**
   * Roles granted in this application.
   */
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinTable(
    name = "STM_APP_ROL",
    joinColumns = @JoinColumn(
      name = "ARO_APPID", foreignKey = @ForeignKey(name = "STM_ARO_FK_APP")),
    inverseJoinColumns = @JoinColumn(
      name = "ARO_ROLEID", foreignKey = @ForeignKey(name = "STM_ARO_FK_ROL")))
  @Builder.Default
  private Set<Role> availableRoles = new HashSet<>();

  /**
   * Trees assigned to this application.
   */
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinTable(
    name = "STM_APP_TREE",
    joinColumns = @JoinColumn(
      name = "ATR_APPID", foreignKey = @ForeignKey(name = "STM_ATR_FK_APP")),
    inverseJoinColumns = @JoinColumn(
      name = "ATR_TREEID", foreignKey = @ForeignKey(name = "STM_ATR_FK_TRE")))
  @Builder.Default
  private Set<Tree> trees = new HashSet<>();

  /**
   * Backgrounds maps.
   */
  @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private Set<ApplicationBackground> backgrounds = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof Application))
      return false;

    Application other = (Application) o;

    return id != null &&
      id.equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

}
