package com.simon.world_banking_app_v1.service;

import com.simon.world_banking_app_v1.domain.dto.EmailDetails;
import com.simon.world_banking_app_v1.domain.entity.UserEntity;
import com.simon.world_banking_app_v1.payload.request.UserRequest;
import com.simon.world_banking_app_v1.payload.response.AccountInfo;
import com.simon.world_banking_app_v1.payload.response.BankResponse;
import com.simon.world_banking_app_v1.repository.UserEntityRepository;
import com.simon.world_banking_app_v1.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserEntityRepository userEntityRepository;
    private final EmailService emailService;

    @Override
    public BankResponse registerUser(UserRequest userRequest) {

        if(userEntityRepository.existsByEmail(userRequest.getEmail())){
            BankResponse response = BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
            return response;
        }

        UserEntity newUser = UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .status("ACTIVE")
                .profilePicture(null)
                .build();

        UserEntity savedUser = userEntityRepository.save(newUser);

        //send email alert
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("ACCOUNT CREATION")
                .messageBody("CONGRATULATIONS!!!, " + savedUser.getFirstName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName() +
                        " Your account has been successfully created. \n Your account number is: " + savedUser.getAccountNumber())
                .build();
        emailService.sendEmailAlert(emailDetails);


        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(savedUser.getAccountBalance())
                        .accountNumber(savedUser.getAccountNumber())
                        .accountName(savedUser.getFirstName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName())
                        .build())
                .build();
    }
}
