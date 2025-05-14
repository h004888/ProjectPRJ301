package com.OLearning.service.impl;

import com.OLearning.dto.UserDTO;
import com.OLearning.entity.User;
import com.OLearning.mapper.UserMapper;
import com.OLearning.repository.UserRepository;
import com.OLearning.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return userMapper.toDTO(user);
        } else {
            return null;
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public void createUser(UserDTO userDTO) {

    }

    @Override
    public void updateUser(UserDTO userDTO) {

    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public UserDTO findByEmail(String email) {
        return null;
    }

    @Override
    public UserDTO findByFullName(String fullName) {
        return null;
    }
}
