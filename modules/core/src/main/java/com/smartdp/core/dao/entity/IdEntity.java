package com.smartdp.core.dao.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 统一定义id的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略. 子类可重载getId()函数重定义id的列名映射和生成策略.
 * 
 * @author pengfenglong
 */
// JPA 基类的标识
@MappedSuperclass
public abstract class IdEntity {

	// @Id
	// @GeneratedValue(generator = "hibernate-uuid")
	// @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
