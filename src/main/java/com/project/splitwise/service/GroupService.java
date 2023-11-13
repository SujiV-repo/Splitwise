package com.project.splitwise.service;

import com.project.splitwise.dto.GroupDto;
import com.project.splitwise.dto.TransactionDTO;
import com.project.splitwise.exception.GroupNotFoundException;

import java.util.List;

public interface GroupService {
    List<TransactionDTO> settleUpByGroupId(int groupId) throws GroupNotFoundException;

    GroupDto createGroup(GroupDto groupDto);

    double totalAmountSpent(int groupId) throws GroupNotFoundException;
}
