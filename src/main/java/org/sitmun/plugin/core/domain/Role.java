package org.sitmun.plugin.core.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

import static org.sitmun.plugin.core.domain.Constants.IDENTIFIER;

/**
 * Role.
 */
@Entity
@Table(name = "STM_ROLE", uniqueConstraints = {
  @UniqueConstraint(name = "STM_ROL_NOM_UK", columnNames = {"ROL_NAME"})})
public class Role {

  /**
   * Unique identifier.
   */
  @TableGenerator(
    name = "STM_ROLE_GEN",
    table = "STM_SEQUENCE",
    pkColumnName = "SEQ_NAME",
    valueColumnName = "SEQ_COUNT",
    pkColumnValue = "ROL_ID",
    allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "STM_ROLE_GEN")
  @Column(name = "ROL_ID")
  private Integer id;

  /**
   * Role name.
   * Role names are unique and cannot be shared across applications.
   */
  @Column(name = "ROL_NAME", length = IDENTIFIER)
  @NotBlank
  private String name;

  /**
   * Role description.
   */
  @Column(name = "ROL_NOTE", length = 500)
  private String description;

  /**
   * Applications that have this role granted.
   */
  @ManyToMany
  @JoinTable(
    name = "STM_APP_ROL",
    joinColumns = @JoinColumn(
      name = "ARO_ROLEID", foreignKey = @ForeignKey(name = "STM_APR_FK_ROL")),
    inverseJoinColumns = @JoinColumn(
      name = "ARO_APPID", foreignKey = @ForeignKey(name = "STM_APR_FK_APP")))
  private Set<Application> applications = new HashSet<>();

  /**
   * Tasks that have this role.
   */
  @ManyToMany
  @JoinTable(
    name = "STM_ROL_TSK",
    joinColumns = @JoinColumn(
      name = "RTS_ROLEID",
      foreignKey = @ForeignKey(name = "STM_RTA_FK_ROL")),
    inverseJoinColumns = @JoinColumn(
      name = "RTS_TASKID",
      foreignKey = @ForeignKey(name = "STM_RTA_FK_T")))
  private Set<Task> tasks;

  /**
   * Permissions that have this role.
   */
  @ManyToMany
  @JoinTable(
    name = "STM_ROL_GGI",
    joinColumns = @JoinColumn(
      name = "RGG_ROLEID",
      foreignKey = @ForeignKey(name = "STM_RGC_FK_ROL")),
    inverseJoinColumns = @JoinColumn(
      name = "RGG_GGIID",
      foreignKey = @ForeignKey(name = "STM_RGC_FK_GCA")))
  private Set<CartographyPermission> permissions;

  public Role() {
  }

  private Role(Integer id, @NotBlank String name, String description, Set<Application> applications,
               Set<Task> tasks, Set<CartographyPermission> permissions) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.applications = applications;
    this.tasks = tasks;
    this.permissions = permissions;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String comments) {
    this.description = comments;
  }

  public Set<Application> getApplications() {
    return applications;
  }

  public void setApplications(Set<Application> applications) {
    this.applications = applications;
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }

  public Set<CartographyPermission> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<CartographyPermission> permissions) {
    this.permissions = permissions;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Role) {
      return ((Role) o).getId().equals(this.getId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  public static class Builder {
    private Integer id;
    private @NotBlank String name;
    private String description;
    private Set<Application> applications = new HashSet<>();
    private Set<Task> tasks = new HashSet<>();
    private Set<CartographyPermission> permissions = new HashSet<>();

    public Builder setId(Integer id) {
      this.id = id;
      return this;
    }

    public Builder setName(@NotBlank String name) {
      this.name = name;
      return this;
    }

    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder setApplications(Set<Application> applications) {
      this.applications = applications;
      return this;
    }

    public Builder setTasks(Set<Task> tasks) {
      this.tasks = tasks;
      return this;
    }

    public Builder setPermissions(Set<CartographyPermission> permissions) {
      this.permissions = permissions;
      return this;
    }

    public Role build() {
      return new Role(id, name, description, applications, tasks, permissions);
    }
  }
}
