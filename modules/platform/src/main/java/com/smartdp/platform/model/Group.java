/*
 * peng
 */

package com.smartdp.platform.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "PLATFORM_GROUP")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Group {
	@Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GROUP_ID", nullable = false)
	private Long groupId;
	
	@Column(name = "GROUP_SIMPLE_NAME", nullable = true)
	private String groupSimpleName;
	
	@Column(name = "GROUP_FULL_NAME", nullable = true)
	private String groupFullName;
	
	@Column(name = "GROUP_CODE", nullable = true)
	private String groupCode;
	
	@Column(name = "GROUP_ADDRESS1", nullable = true)
	private String groupAddress1;
	
	@Column(name = "GROUP_ADDRESS2", nullable = true)
	private String groupAddress2;
	
	@Column(name = "GROUP_TEL1", nullable = true)
	private String groupTel1;
	
	@Column(name = "GROUP_TEL2", nullable = true)
	private String groupTel2;
	
	@Column(name = "GROUP_BEGIN_DATE", nullable = true)
	private Date groupBeginDate;
	
	@Column(name = "GROUP_TYPE", nullable = true)
	private String groupType;
	
	@Column(name = "GROUP_FAX", nullable = true)
	private String groupFax;
	
	@Column(name = "GROUP_POSTAL", nullable = true)
	private String groupPostal;
	
	@Column(name = "GROUP_LEGAL", nullable = true)
	private String groupLegal;
	
	@Column(name = "GROUP_TAX_NO", nullable = true)
	private String groupTaxNo;
	
	@Column(name = "GROUP_REG_NO", nullable = true)
	private String groupRegNo;
	
	@Column(name = "GROUP_BELONG_DIST", nullable = true)
	private Long groupBelongDist;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_PARENT")
	private Group parentGroup;
	
	@Column(name = "STATUS", nullable = false)
	private String status;
	
	@Column(name = "MEMO", nullable = true)
	private String memo;
	
	@Column(name = "CREATOR", nullable = true)
	private String creator;
	
	@Column(name = "CREATE_DATE", nullable = false)
	private Timestamp createDate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentGroup")
	private List<Group> groups = new ArrayList<Group>();
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "PLATFORM_GROUP_ROLE_REF",
       		joinColumns = { @JoinColumn(name = "GROUP_ID") },
	       	inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<Role> roles = new HashSet<Role>(0);
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy="groups")
	private Set<User> users = new HashSet<User>(0);
	
	private String state;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupSimpleName() {
		return groupSimpleName;
	}

	public void setGroupSimpleName(String groupSimpleName) {
		this.groupSimpleName = groupSimpleName;
	}

	public String getGroupFullName() {
		return groupFullName;
	}

	public void setGroupFullName(String groupFullName) {
		this.groupFullName = groupFullName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupAddress1() {
		return groupAddress1;
	}

	public void setGroupAddress1(String groupAddress1) {
		this.groupAddress1 = groupAddress1;
	}

	public String getGroupAddress2() {
		return groupAddress2;
	}

	public void setGroupAddress2(String groupAddress2) {
		this.groupAddress2 = groupAddress2;
	}

	public String getGroupTel1() {
		return groupTel1;
	}

	public void setGroupTel1(String groupTel1) {
		this.groupTel1 = groupTel1;
	}

	public String getGroupTel2() {
		return groupTel2;
	}

	public void setGroupTel2(String groupTel2) {
		this.groupTel2 = groupTel2;
	}

	public Date getGroupBeginDate() {
		return groupBeginDate;
	}

	public void setGroupBeginDate(Date groupBeginDate) {
		this.groupBeginDate = groupBeginDate;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getGroupFax() {
		return groupFax;
	}

	public void setGroupFax(String groupFax) {
		this.groupFax = groupFax;
	}

	public String getGroupPostal() {
		return groupPostal;
	}

	public void setGroupPostal(String groupPostal) {
		this.groupPostal = groupPostal;
	}

	public String getGroupLegal() {
		return groupLegal;
	}

	public void setGroupLegal(String groupLegal) {
		this.groupLegal = groupLegal;
	}

	public String getGroupTaxNo() {
		return groupTaxNo;
	}

	public void setGroupTaxNo(String groupTaxNo) {
		this.groupTaxNo = groupTaxNo;
	}

	public String getGroupRegNo() {
		return groupRegNo;
	}

	public void setGroupRegNo(String groupRegNo) {
		this.groupRegNo = groupRegNo;
	}

	public Long getGroupBelongDist() {
		return groupBelongDist;
	}

	public void setGroupBelongDist(Long groupBelongDist) {
		this.groupBelongDist = groupBelongDist;
	}

	public Group getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(Group parentGroup) {
		this.parentGroup = parentGroup;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getState() {
		if(groups.size() > 0){
			return "closed";
		}else{
			return "open";
		}
	}
	public void setState(String state) {
		this.state = state;
	}
}
