package com.project.splitwise.service;

import com.project.splitwise.dto.GroupDto;
import com.project.splitwise.dto.TransactionDTO;
import com.project.splitwise.exception.GroupNotFoundException;
import com.project.splitwise.model.Group;
import com.project.splitwise.repository.GroupRepository;
import com.project.splitwise.service.strategy.SettleUpStrategies;
import com.project.splitwise.service.strategy.SettleUpStrategy;
import com.project.splitwise.service.strategy.SettleUpStrategyFactory;
import jdk.jfr.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TransactionDTO> settleUpByGroupId(int groupId) throws GroupNotFoundException {
        SettleUpStrategy strategy = SettleUpStrategyFactory.getSettleUpStrategy(SettleUpStrategies.HeapBased_SettleUpStrategy);
        Optional<Group> savedGroup = groupRepository.findById(groupId);

        if(savedGroup.isEmpty()){
            throw new GroupNotFoundException("Group for the given Id is not found. Id : "+groupId );
        }

        List<TransactionDTO> transactions = strategy.settleUp(savedGroup.get().getExpenses());
        return transactions;
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        Group group = modelMapper.map(groupDto, Group.class);
        Group savedGroup = groupRepository.save(group);
        return modelMapper.map(savedGroup, GroupDto.class);
    }

}
