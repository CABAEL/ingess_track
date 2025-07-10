package com.ingress_track.mapper;

import com.ingress_track.dto.UserDto;
import com.ingress_track.entity.User;

public class UserMapper
{
    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getUserType(),
                user.getUserStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getMiddleName(),
                userDto.getLastName(),
                userDto.getUserType(),
                userDto.getUserStatus(),
                userDto.getCreatedAt(),
                userDto.getUpdatedAt()
        );
    }
}
