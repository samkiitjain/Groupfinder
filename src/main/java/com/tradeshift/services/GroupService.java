package com.tradeshift.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tradeshift.api.request.UpdateParentGroupRequest;
import com.tradeshift.entity.GroupEntity;
import com.tradeshift.repository.GroupRepository;

@Component
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

	public List<GroupEntity> getDescendantGroup(String groupCode) {

		GroupEntity ge = groupRepository.findByNodeType(groupCode);
		return groupRepository.findByParentNodeIdStartsWith(ge.getNodeId());
	}

	public GroupEntity updateParentGroup(UpdateParentGroupRequest request) {

		GroupEntity targetedChildGroup = groupRepository.findByNodeType(request.getGroupType());
		
		GroupEntity targetParentGroup = groupRepository.findByNodeType(request.getTargetParentType());

		List<GroupEntity> targetParentExistingChildGroups = groupRepository
							.findByParentNodeIdStartsWithAndNodeHeight
										(targetParentGroup.getNodeId(), targetParentGroup.getNodeHeight() + 1);
		//Update node information.
		targetedChildGroup.setNodeHeight(targetParentGroup.getNodeHeight() + 1);
		targetedChildGroup.setParentNodeId(targetParentGroup.getNodeId());
		targetedChildGroup.setNodeId(targetParentGroup.getNodeId() + (targetParentExistingChildGroups.size() + 1));

		return groupRepository.save(targetedChildGroup);
	}
}
