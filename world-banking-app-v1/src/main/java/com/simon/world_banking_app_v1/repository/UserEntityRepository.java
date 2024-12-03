package com.simon.world_banking_app_v1.repository;

import com.simon.world_banking_app_v1.domain.entity.UserEntity;
import com.simon.world_banking_app_v1.payload.request.EnquiryRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity,  Long> {
    boolean existsByEmail(String email);

    boolean existsByAccountNumber(String accountNumber);

    UserEntity findByAccountNumber(String accountNumber);
}
