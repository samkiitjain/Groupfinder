package com.tradeshift.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradeshift.api.request.UpdateParentGroupRequest;
import com.tradeshift.entity.GroupEntity;
import com.tradeshift.exception.InvalidInputException;
import com.tradeshift.services.GroupService;

@RestController
@RequestMapping(path = "/api")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@GetMapping(value = "/group/descendents/{groupCode}", produces = "application/json")
	public ResponseEntity<List<GroupEntity>> getGroupDescendents(@PathVariable String groupCode) {
		return ResponseEntity.status(HttpStatus.OK).body(groupService.getDescendantGroup(groupCode));
	}

	@PostMapping(value = "/update/parent", produces = "application/json")
	public ResponseEntity<GroupEntity> upddateParent(@RequestBody UpdateParentGroupRequest request) {
		if(!request.validate()) {
			throw new InvalidInputException("Invalid input passed.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(groupService.updateParentGroup(request));
	}
}
