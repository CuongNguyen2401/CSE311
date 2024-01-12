package com.fastfood.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "fullname")
	private String fullName;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private int status;

	@ManyToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	@JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "accountid"), inverseJoinColumns = @JoinColumn(name = "roleid"))
	private List<RoleEntity> roles = new ArrayList<RoleEntity>();
	
	@OneToMany(mappedBy = "accountEntity")
	private List<OrderEntity> orders = new ArrayList<>();
	
	@OneToMany(mappedBy = "accountEntity")
	private List<NewsEntity> news = new ArrayList<>();
	
	

}
