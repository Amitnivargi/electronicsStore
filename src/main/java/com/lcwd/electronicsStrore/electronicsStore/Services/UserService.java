package com.lcwd.electronicsStrore.electronicsStore.Services;

import com.lcwd.electronicsStrore.electronicsStore.DTO.UserDto;
import com.lcwd.electronicsStrore.electronicsStore.Entities.User;

import java.util.List;

public interface UserService {
    // we cannot use User class directly in UserService
   // User creteUser(User user);
    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto,String userId);

    void deleteUser(String userId);

    List<UserDto> getAllUser();

    UserDto getUserById(String userId);

    UserDto getUserByEmail(String userId);

    //search user
    List<UserDto> searchUser(String keyword);

}
