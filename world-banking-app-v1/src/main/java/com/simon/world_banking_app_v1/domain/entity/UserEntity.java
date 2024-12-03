package com.simon.world_banking_app_v1.domain.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.simon.world_banking_app_v1.domain.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "users_tbl")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseClass {

    private String firstName;
    private String lastName;
    private String otherName;
    private String email;
    private String password;
    private String gender;
    private String address;
    private String stateOfOrigin;
    private String accountNumber;
    private BigDecimal accountBalance;
    private String phoneNumber;
    private String profilePicture;//Don't store sensitive things as cloudinary

    @Enumerated(EnumType.STRING)
    private Role role;

    private String status;




}
