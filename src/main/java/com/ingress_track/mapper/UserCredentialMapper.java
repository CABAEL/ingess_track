package com.ingress_track.mapper;


import com.ingress_track.dto.UserCredentialDto;
import com.ingress_track.dto.UserDto;
import com.ingress_track.entity.User;
import com.ingress_track.entity.UserCredential;


public class UserCredentialMapper{

    public static UserCredentialDto mapToUserCredentialDto(UserCredential userCredential){
        return new UserCredentialDto(
        userCredential.getUserId(),
        userCredential.getUserName(),
        userCredential.getPassWord(),
        userCredential.getRole(),
        userCredential.getCreatedAt(),
        userCredential.getUpdatedAt()
        );
    }

    public static UserCredential mapToUserCredential(UserCredentialDto dto, UserDto userDto) {

        User user = UserMapper.mapToUser(userDto);

        return new UserCredential(
                user,
                dto.getUser_id(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getRole(),
                dto.getCreatedAt(),
                dto.getUpdatedAt()
        );
    }




}
