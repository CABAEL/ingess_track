package com.ingress_track.repository;

import com.ingress_track.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository <UserCredential,Long> {
    Optional<UserCredential> findByUserName(String userName);
}
