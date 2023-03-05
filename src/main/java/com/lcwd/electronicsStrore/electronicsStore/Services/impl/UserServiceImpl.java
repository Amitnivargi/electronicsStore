package com.lcwd.electronicsStrore.electronicsStore.Services.impl;

import com.lcwd.electronicsStrore.electronicsStore.DTO.UserDto;
import com.lcwd.electronicsStrore.electronicsStore.Entities.User;
import com.lcwd.electronicsStrore.electronicsStore.Exceptions.ResourceNotFoundException;
import com.lcwd.electronicsStrore.electronicsStore.Services.UserService;
import com.lcwd.electronicsStrore.electronicsStore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public UserDto createUser(UserDto userDto) {

        //generating unique id in string formate
        String userId= UUID.randomUUID().toString();
        userDto.setUserId(userId);
        //dto ->entity
        User user=dtoToEntity(userDto);
        User saveuser=userRepository.save(user);
        UserDto newDto=enttityToDto(saveuser);
        return newDto;
    }


    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        User updateUser = userRepository.save(user);
        UserDto updatedDto = enttityToDto(updateUser);
        return updatedDto;


    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);


    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> all = userRepository.findAll();
        List<UserDto> dtoList = all.stream().map(user -> enttityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return enttityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with emailid"));
        return enttityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> enttityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    private UserDto enttityToDto(User saveUser) {
        // we are done manually
//        UserDto userDto = UserDto.builder()
//                .userId(saveUser.getUserId())
//                .name(saveUser.getName())
//                .email(saveUser.getEmail())
//                .password(saveUser.getPassword())
//                .about(saveUser.getAbout())
//                .gender(saveUser.getGender())
//                .imageName(saveUser.getImageName())
//                .build();
      //  return userDto;
        //using modelMapper
        return mapper.map(saveUser, UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {
//        User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName()).build();
//
//        return user;

        return mapper.map(userDto,User.class);
    }
}
