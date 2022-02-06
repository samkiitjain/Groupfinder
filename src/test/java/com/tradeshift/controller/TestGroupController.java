package com.tradeshift.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.tradeshift.api.request.UpdateParentGroupRequest;
import com.tradeshift.controllers.GroupController;
import com.tradeshift.entity.GroupEntity;
import com.tradeshift.exception.InvalidInputException;
import com.tradeshift.services.GroupService;

@ExtendWith(MockitoExtension.class)
public class TestGroupController {

	@Mock
	private GroupService groupService;

	@InjectMocks
	private GroupController groupController;

	@Test
	public void testGetGroupDescendents() {
		when(groupService.getDescendantGroup("groupa")).thenReturn(groupDescendentResponse());

		ResponseEntity<List<GroupEntity>> descendents = groupController.getGroupDescendents("groupa");

		assertTrue(descendents.getBody().size() > 0);
		
	}

	@Test
	public void testUpdateParent_InvalidChildGroup() {
		assertThrows(InvalidInputException.class, () -> {
			 groupController.updateParent(getRequest("invalidchild"));
		 });
	}

	@Test
	public void testUpdateParent_InvalidParentGroup() {
		assertThrows(InvalidInputException.class, () -> {
			 groupController.updateParent(getRequest("invalidParent"));
		 });
	}

	@Test
	public void testUpdateParent_Success() {
		UpdateParentGroupRequest request = getRequest("success");
		when(groupService.updateParentGroup(request)).thenReturn(updatedGroupResponseMock());

		ResponseEntity<GroupEntity> descendents = groupController.updateParent(request);

		assertNotNull(descendents.getBody());
	}

	private List<GroupEntity> groupDescendentResponse() {
		List<GroupEntity> descendentList = new ArrayList<GroupEntity>();
		GroupEntity descendents = new GroupEntity();
		descendents.setIndex(2);
		descendents.setNodeHeight(1);
		descendents.setNodeName("Test Group");
		descendents.setNodeType("groupab");
		descendents.setParentNodeId("0");
		descendentList.add(descendents);
		return descendentList;
	}

	private UpdateParentGroupRequest getRequest(String scenario) {
		UpdateParentGroupRequest request = new UpdateParentGroupRequest();;
		switch(scenario) {
		case "invalidchild" : 
			request.setGroupType(null);
			break;
		case "invalidParent" :
			request.setGroupType("groupa");
			request.setTargetParentType(null);
			break;
		case "success" :
			request.setGroupType("groupa");
			request.setTargetParentType("groupba");
			break;
		}
		return request;
	}

	private GroupEntity updatedGroupResponseMock() {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setIndex(1);
		groupEntity.setNodeHeight(1);
		groupEntity.setNodeId("1");
		groupEntity.setNodeName("Test");
		groupEntity.setNodeType("groupbaa");
		groupEntity.setParentNodeId("groupba");
		return groupEntity;
	}
}
