package com.ingress_track.service.impl;

import com.ingress_track.dto.UserDto;
import com.ingress_track.entity.User;
import com.ingress_track.mapper.UserMapper;
import com.ingress_track.repository.UserRepository;
import com.ingress_track.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = UserMapper.mapToUser(userDto);
        User mapToUser = userRepository.save(user);

        return UserMapper.mapToUserDto(mapToUser);

    }
}
