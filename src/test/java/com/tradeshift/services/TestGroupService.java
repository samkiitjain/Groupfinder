package com.tradeshift.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tradeshift.api.request.UpdateParentGroupRequest;
import com.tradeshift.entity.GroupEntity;
import com.tradeshift.repository.GroupRepository;

@ExtendWith(MockitoExtension.class)
public class TestGroupService {

	@Mock
	private GroupRepository groupRepository;

	@InjectMocks
	private GroupService groupService;

	@Test
	public void testGetDescendantGroup() {
		when(groupRepository.findByNodeType("groupB")).thenReturn(mockChildGroup());
		when(groupRepository.findByParentNodeIdStartsWith("11")).thenReturn(descendentGroupsList());

		List<GroupEntity> descendentsList = groupService.getDescendantGroup("groupB");

		Assertions.assertTrue(descendentsList.size() > 0);
	}

	@Test
	public void testUpdateParentGroup() {
		
		GroupEntity childGroup = mockChildGroup();
		when(groupRepository.findByNodeType("groupaba")).thenReturn(childGroup);
		when(groupRepository.findByNodeType("groupba")).thenReturn(mockParentGroup());
		when(groupRepository.findByParentNodeIdStartsWithAndNodeHeight("21", 3)).thenReturn(descendentGroupsList());
		mockGroupWithNewParentDetails(childGroup);
		when(groupRepository.save(childGroup)).thenReturn(childGroup);

		GroupEntity updatedGroup = groupService.updateParentGroup(createMockRequest());

		Assertions.assertEquals(updatedGroup.getNodeHeight(), 3);
		Assertions.assertEquals(updatedGroup.getNodeId(), "213");
	}

	private GroupEntity mockChildGroup() {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setIndex(1);
		groupEntity.setNodeId("11");
		groupEntity.setParentNodeId("1");
		groupEntity.setNodeHeight(1);
		return groupEntity;
	}

	private GroupEntity mockParentGroup() {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setIndex(1);
		groupEntity.setNodeId("21");
		groupEntity.setParentNodeId("2");
		groupEntity.setNodeHeight(2);
		return groupEntity;
	}

	private void mockGroupWithNewParentDetails(GroupEntity groupEntity) {
		groupEntity.setIndex(1);
		groupEntity.setNodeId("213");
		groupEntity.setParentNodeId("21");
		groupEntity.setNodeHeight(3);
	}

	private List<GroupEntity> descendentGroupsList() {
		List<GroupEntity> groupList = new ArrayList<GroupEntity>();
		for(int i=0; i<2; i++) {
			GroupEntity groupEntity = new GroupEntity();
			groupEntity.setIndex(i);
			groupEntity.setNodeId("21" + (i+1));
			groupList.add(groupEntity);
		}
		return groupList;
	}

	private UpdateParentGroupRequest createMockRequest() {
		UpdateParentGroupRequest request = new UpdateParentGroupRequest();
		request.setGroupType("groupaba");
		request.setTargetParentType("groupba");
		return request;
	}
}
