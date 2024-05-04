package com.rest.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Role")
public class Role {
	
	public Role() {
		super();
	}

	public Role(String roleName, String roleDescription) {
		super();
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}

	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", roleDescription=" + roleDescription + "]";
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Id
	@Column(name="roleName")
	private String roleName;
	
	@Column(name="role_description")
	private String roleDescription;
	
}
