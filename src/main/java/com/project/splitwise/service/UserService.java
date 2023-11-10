package com.project.splitwise.service;

import com.project.splitwise.dto.GroupDto;
import com.project.splitwise.dto.UserDto;
import com.project.splitwise.exception.GroupNotFoundException;
import com.project.splitwise.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto userDto);
    void addUserToGroup(int userId, int groupId) throws UserNotFoundException, GroupNotFoundException;
    UserDto getUserById(int userId) throws UserNotFoundException;
    List<GroupDto> getUserGroups(int userId) throws UserNotFoundException;
}
