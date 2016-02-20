/*
 * pengfenglong
 */

package com.smartdp.platform.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "PLATFORM_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", nullable = false)
	private Long userId;
	
	@Column(name = "USER_CODE", nullable = true)
	private String userCode;
	
	@Column(name = "USER_NAME", nullable = true)
	private String userName;
	
	@Column(name = "USER_PASSWORD", nullable = true)
	private String userPassword;
	
	@Column(name = "USER_GENDER", nullable = true)
	private String userGender;
	
	@Column(name = "USER_POSITION", nullable = true)
	private String userPosition;
	
	@Column(name = "USER_PHOTO_URL", nullable = true)
	private String userPhotoUrl;
	
	@Column(name = "USER_QQ", nullable = true)
	private String userQq;
	
	@Column(name = "USER_MSN", nullable = true)
	private String userMsn;
	
	@Column(name = "USER_MOBILE", nullable = true)
	private String userMobile;
	
	@Column(name = "USER_MOBILE2", nullable = true)
	private String userMobile2;
	
	@Column(name = "USER_OFFICE_TEL", nullable = true)
	private String userOfficeTel;
	
	@Column(name = "USER_ADDRESS", nullable = true)
	private String userAddress;
	
	@Column(name = "USER_FAMILY_TEL", nullable = true)
	private String userFamilyTel;
	
	@Column(name = "USER_EMAIL", nullable = true)
	private String userEmail;
	
	@Column(name = "USER_AVIDATE", nullable = true)
	private Date userAvidate;
	
	@Column(name = "USER_IS_AGENT", nullable = true)
	private String userIsAgent;
	
	@Column(name = "USER_BELONGTO_ORG", nullable = true)
	private Long userBelongtoOrg;
	
	@Column(name = "MEMO", nullable = true)
	private String memo;
	
	@Column(name = "STATUS", nullable = false)
	private String status;
	
	@Column(name = "CREATOR", nullable = true)
	private String creator;
	
	@Column(name = "CREATE_DATE", nullable = false)
	private Timestamp createDate;
	
	@Column(name = "USER_NAME_PY", nullable = true)
	private String userNamePy;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch = FetchType.LAZY)
    @JoinTable(
            name = "PLATFORM_GROUP_USER_REF",
       		joinColumns = { @JoinColumn(name = "USER_ID") },
	       	inverseJoinColumns = @JoinColumn(name = "GROUP_ID"))
	private Set<Group> groups = new HashSet<Group>(0);
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch = FetchType.LAZY)
    @JoinTable(
            name = "PLATFORM_ROLE_USER_REF",
       		joinColumns = { @JoinColumn(name = "USER_ID") },
	       	inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<Role> roles = new HashSet<Role>(0);

	public Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(Long value) {
		this.userId = value;
	}

	public String getUserCode() {
		return this.userCode;
	}
	
	public void setUserCode(String value) {
		this.userCode = value;
	}

	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String value) {
		this.userName = value;
	}

	public String getUserPassword() {
		return this.userPassword;
	}
	
	public void setUserPassword(String value) {
		this.userPassword = value;
	}

	public String getUserGender() {
		return this.userGender;
	}
	
	public void setUserGender(String value) {
		this.userGender = value;
	}

	public String getUserPosition() {
		return this.userPosition;
	}
	
	public void setUserPosition(String value) {
		this.userPosition = value;
	}

	public String getUserPhotoUrl() {
		return this.userPhotoUrl;
	}
	
	public void setUserPhotoUrl(String value) {
		this.userPhotoUrl = value;
	}

	public String getUserQq() {
		return this.userQq;
	}
	
	public void setUserQq(String value) {
		this.userQq = value;
	}

	public String getUserMsn() {
		return this.userMsn;
	}
	
	public void setUserMsn(String value) {
		this.userMsn = value;
	}

	public String getUserMobile() {
		return this.userMobile;
	}
	
	public void setUserMobile(String value) {
		this.userMobile = value;
	}

	public String getUserMobile2() {
		return this.userMobile2;
	}
	
	public void setUserMobile2(String value) {
		this.userMobile2 = value;
	}

	public String getUserOfficeTel() {
		return this.userOfficeTel;
	}
	
	public void setUserOfficeTel(String value) {
		this.userOfficeTel = value;
	}

	public String getUserAddress() {
		return this.userAddress;
	}
	
	public void setUserAddress(String value) {
		this.userAddress = value;
	}

	public String getUserFamilyTel() {
		return this.userFamilyTel;
	}
	
	public void setUserFamilyTel(String value) {
		this.userFamilyTel = value;
	}

	public String getUserEmail() {
		return this.userEmail;
	}
	
	public void setUserEmail(String value) {
		this.userEmail = value;
	}

	public Date getUserAvidate() {
		return this.userAvidate;
	}
	
	public void setUserAvidate(Date value) {
		this.userAvidate = value;
	}

	public String getUserIsAgent() {
		return this.userIsAgent;
	}
	
	public void setUserIsAgent(String value) {
		this.userIsAgent = value;
	}

	public Long getUserBelongtoOrg() {
		return this.userBelongtoOrg;
	}
	
	public void setUserBelongtoOrg(Long value) {
		this.userBelongtoOrg = value;
	}

	public String getMemo() {
		return this.memo;
	}
	
	public void setMemo(String value) {
		this.memo = value;
	}

	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}

	public String getCreator() {
		return this.creator;
	}
	
	public void setCreator(String value) {
		this.creator = value;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(Timestamp value) {
		this.createDate = value;
	}

	public String getUserNamePy() {
		return this.userNamePy;
	}
	
	public void setUserNamePy(String value) {
		this.userNamePy = value;
	}
	
	public void setGroups(Set<Group> groups){
		this.groups = groups;
	}
	
	public Set<Group> getGroups() {
		return groups;
	}
	
	public void setRoles(Set<Role> roles){
		this.roles = roles;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
}
