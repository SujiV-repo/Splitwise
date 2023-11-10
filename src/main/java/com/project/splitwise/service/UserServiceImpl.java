package com.project.splitwise.service;

import com.project.splitwise.dto.GroupDto;
import com.project.splitwise.dto.UserDto;
import com.project.splitwise.exception.GroupNotFoundException;
import com.project.splitwise.exception.UserNotFoundException;
import com.project.splitwise.model.Group;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.GroupRepository;
import com.project.splitwise.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public void addUserToGroup(int userId, int groupId) throws UserNotFoundException, GroupNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User is not registered with Id : " + userId));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException("Group is not created for the Id : "+ groupId));

        user.getGroups().add(group);
        userRepository.save(user);
    }

    @Override
    public UserDto getUserById(int userId) throws UserNotFoundException {
        User user = fetchUserForId(userId);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public List<GroupDto> getUserGroups(int userId) throws UserNotFoundException {
        User user = fetchUserForId(userId);
        List<Group> groups = user.getGroups();
        return convertToGroupDTOs(groups);
    }

    private User fetchUserForId(int userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User is not registered with Id : "+ userId));
    }

    private List<GroupDto> convertToGroupDTOs(List<Group> groups){
        return groups.stream()
                .map(this::convertToGroupDTO)
                .collect(Collectors.toList());
    }

    private GroupDto convertToGroupDTO(Group group) {
        return modelMapper.map(group, GroupDto.class);
    }
}
