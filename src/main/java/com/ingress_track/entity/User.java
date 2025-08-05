package com.ingress_track.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "it_users")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name" , nullable = false, length = 60)
	@Size(max = 60)
	private String firstName;

	@Column(name = "middle_name", length = 60)
	@Size(max = 60)
	private String middleName;

	@Column(name = "last_name", length = 60)
	@Size(max = 60)
	private String lastName;

	@Column(name = "status")
	private int userStatus;

	@Column(name = "user_type", nullable = false)
	private int userType;

	@CreationTimestamp
	@Column(name = "created_at" , updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;


}
