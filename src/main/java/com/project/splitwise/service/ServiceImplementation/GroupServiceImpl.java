package com.project.splitwise.service.ServiceImplementation;

import com.project.splitwise.dto.GroupDto;
import com.project.splitwise.dto.TransactionDTO;
import com.project.splitwise.exception.GroupNotFoundException;
import com.project.splitwise.model.Expense;
import com.project.splitwise.model.Group;
import com.project.splitwise.repository.GroupRepository;
import com.project.splitwise.service.GroupService;
import com.project.splitwise.service.strategy.SettleUpStrategies;
import com.project.splitwise.service.strategy.SettleUpStrategy;
import com.project.splitwise.service.strategy.SettleUpStrategyFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ModelMapper modelMapper;

    //Settling up each user expenses in a group and getting the who owes how much to others in the group
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

    //Creating a new group to track expenses
    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        Group group = modelMapper.map(groupDto, Group.class);
        Group savedGroup = groupRepository.save(group);
        return modelMapper.map(savedGroup, GroupDto.class);
    }

    //Getting total Amount spent by each group
    @Override
    public double totalAmountSpent(int groupId) throws GroupNotFoundException {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group doesn't exist"));
        List<Expense> expenses = group.getExpenses();

        double totalAmount = 0;
        for(Expense expense : expenses){
            totalAmount = totalAmount + expense.getAmount();
        }

        group.setTotalAmountSpent(totalAmount);
        groupRepository.save(group);
        return totalAmount;
    }

}
