package com.rest.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name="user")
public class User implements UserDetails {
	
	@Override
	public String toString() {
		
		return "User [userName=" + username + ", first_name=" + first_name + ", last_name=" + last_name + ", password="
				+ password + ", roles=" + roles + "]";
	}
	
	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		System.out.println(roles.toString());
		this.roles = roles;
	}

	@Id
	@Column(name="user_name")
	private String username;
	
	@Column(name="first_name")
	private String first_name;
	
	@Column(name="last_name")
	private String last_name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="account_locked")
	private boolean accountLocked;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", 
	joinColumns = {
			@JoinColumn(name="USER_ID")
	},
	inverseJoinColumns = {
			@JoinColumn(name="ROLE_ID")
	}
	)
	private Set<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRoleName()))
				.collect(Collectors.toSet());
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}	
}
