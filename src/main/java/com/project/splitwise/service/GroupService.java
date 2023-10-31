package com.project.splitwise.service;

import com.project.splitwise.dto.TransactionDTO;

import java.util.List;

public interface GroupService {
    List<TransactionDTO> settleUpByGroupId(int groupId);
}
