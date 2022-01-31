package com.tradeshift.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
@Entity
@Table(name = "GROUPS")
public class GroupEntity {

	@Column(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer index;

	@Column(name = "NODE_ID")
	private String nodeId;

	@Column(name = "NODE_PARENT_ID")
	private String parentNodeId;

	@Column(name = "NODE_HEIGHT")
	private Integer nodeHeight;

	@Column(name = "NODE_NAME")
	private String nodeName;

	@Column(name = "NODE_TYPE")
	private String nodeType;
}
