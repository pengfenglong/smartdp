package com.smartdp.weixin.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 微信会员
 * 
 * @author pengfenglong
 * 
 */
@Entity
@Table(name = "WEIXIN_MEMBER")
public class Member {

	/**
	 * 商户会员ID
	 */
	// @Id
	// @GeneratedValue(generator = "paymentableGenerator")
	// @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	// @Column(name = "uId", nullable = false, length = 32)
	// private String id;

	@Id
	@javax.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * 微信登陆账号
	 */
	@Column(name = "weixinLoginAccount")
	private String weixinloginAccount;

	/**
	 * 微信登陆密码
	 */
	@Column(name = "weixinLoginPwd")
	private String weixinLoginPwd;

	/**
	 * 描述
	 */
	@Column(name = "contact")
	private String contact;

	/**
	 * 创建日期
	 */
	@Column(name = "createDate")
	private Date createDate;

	/**
	 * 标示：用户微信api接入url生成，这个可以把微信用户发送消息的对象对应起来
	 */
	@Column(name = "mark")
	private String mark;

	/**
	 * 所属用户
	 */
	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWeixinloginAccount() {
		return weixinloginAccount;
	}

	public void setWeixinloginAccount(String weixinloginAccount) {
		this.weixinloginAccount = weixinloginAccount;
	}

	public String getWeixinLoginPwd() {
		return weixinLoginPwd;
	}

	public void setWeixinLoginPwd(String weixinLoginPwd) {
		this.weixinLoginPwd = weixinLoginPwd;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

}
