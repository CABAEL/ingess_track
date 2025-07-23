package com.ingress_track.mapper;


import com.ingress_track.dto.UserCredentialDto;
import com.ingress_track.entity.UserCredential;


public class UserCredentialMapper{

    public static UserCredentialDto mapToUserCredentialDto(UserCredential userCredential){
        return new UserCredentialDto(
        userCredential.getUserId(),
        userCredential.getUserName(),
        userCredential.getPassWord(),
        userCredential.getCreatedAt(),
        userCredential.getUpdatedAt()
        );
    }

    public static UserCredential mapToUserCredential(UserCredentialDto dto) {
        UserCredential entity = new UserCredential();
        entity.setUserName(dto.getUsername());
        entity.setPassWord(dto.getPassword());

        // Let JPA handle timestamps and ID via User relationship
        return entity;
    }


}
