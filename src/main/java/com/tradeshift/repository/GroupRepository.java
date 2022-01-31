package com.tradeshift.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradeshift.entity.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer>  {

	public List<GroupEntity> findByParentNodeIdStartsWith(String parentId);

	public GroupEntity findByNodeType(String nodeType);

	public List<GroupEntity> findByParentNodeIdStartsWithAndNodeHeight(String parentId, Integer nodeHeight);
}
