package com.project.splitwise.controller;

import com.project.splitwise.dto.GroupDto;
import com.project.splitwise.dto.TransactionDTO;
import com.project.splitwise.exception.GroupNotFoundException;
import com.project.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/settleup/{groupId}")
    public ResponseEntity settleUpForGroup(@PathVariable("groupId") int groupId) throws GroupNotFoundException {
        List<TransactionDTO> transactions = groupService.settleUpByGroupId(groupId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/create")
    public ResponseEntity createGroup(@RequestBody GroupDto groupDto){
        GroupDto savedGroup = groupService.createGroup(groupDto);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }

    @GetMapping("/{groupId}/totalAmount")
    public ResponseEntity getTotalAmount(@PathVariable int groupId) throws GroupNotFoundException {
        double totalAmount = groupService.totalAmountSpent(groupId);
        return new ResponseEntity<>("Total Amount spent by the Group is : "+totalAmount ,HttpStatus.OK);
    }
}
