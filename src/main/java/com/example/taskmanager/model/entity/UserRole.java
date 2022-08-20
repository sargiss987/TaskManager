package com.example.taskmanager.model.entity;

import com.example.taskmanager.model.enums.RoleType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "role_type", nullable = false)
  private String roleType;

  public UserRole() {}

  public UserRole(Long id, String roleType) {
    this.id = id;
    this.roleType = roleType;
  }

  public static UserRole isManager() {
    return new UserRole(1L, RoleType.MANAGER.name());
  }

  public static UserRole isEmployee() {
    return new UserRole(2L, RoleType.EMPLOYEE.name());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoleType() {
    return roleType;
  }

  public void setRoleType(String roleType) {
    this.roleType = roleType;
  }
}
