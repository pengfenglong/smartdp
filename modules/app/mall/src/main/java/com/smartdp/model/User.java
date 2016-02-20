package com.smartdp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.smartdp.core.dao.entity.IdEntity;

/**
 * 商城用户
 * 
 * @author pengfenglong
 */
@Entity
@Table(name = "mall_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity {
	
	/** 登陆用户名 */
	@Column(name = "user_name")
	private String userName;

	/** 登陆用户密码 */
	@Column(name = "pwd")
	private String pwd;

	/** 用户真实姓名 */
	@Column(name = "name")
	private String name;
	
	/** 手机号码 */
	@Column(name = "phone")
	private String phone;

	/** 家庭住址 */
	@Column(name = "address")
	private String address;
	
	/** 邮编 */
	@Column(name = "postalcode")
	private String postalcode;
	
	/** 邮箱 */
	@Column(name = "email")
	private String email;

	/** 性别 */
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "create_date")
	private java.sql.Timestamp createDate;

	@Column(name = "status")
	private String status;

	/** 积分 */
	@Column(name = "integration")
	private String integration;


	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Comment> comments = new HashSet<Comment>(0);


	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Favorite> favorites = new HashSet<Favorite>(0);

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Order> orders = new HashSet<Order>(0);

	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Shoppingcart> shoppingcarts = new HashSet<Shoppingcart>(0);

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public java.sql.Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.sql.Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIntegration() {
		return integration;
	}

	public void setIntegration(String integration) {
		this.integration = integration;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Shoppingcart> getShoppingcarts() {
		return shoppingcarts;
	}

	public void setShoppingcarts(Set<Shoppingcart> shoppingcarts) {
		this.shoppingcarts = shoppingcarts;
	}

}
