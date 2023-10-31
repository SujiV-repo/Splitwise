package com.project.splitwise.service;

import com.project.splitwise.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupServiceImpl implements GroupService{
    @Override
    public List<TransactionDTO> settleUpByGroupId(int groupId) {
        return null;
    }
}
