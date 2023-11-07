package com.project.splitwise.service;

import com.project.splitwise.dto.UserDto;
import com.project.splitwise.exception.GroupNotFoundException;
import com.project.splitwise.exception.UserNotFoundException;

public interface UserService {
    UserDto registerUser(UserDto userDto);
    void addUserToGroup(int userId, int groupId) throws UserNotFoundException, GroupNotFoundException;
}
