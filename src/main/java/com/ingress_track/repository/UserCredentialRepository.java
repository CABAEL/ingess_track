package com.ingress_track.repository;

import com.ingress_track.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository <UserCredential,Long> {

}
