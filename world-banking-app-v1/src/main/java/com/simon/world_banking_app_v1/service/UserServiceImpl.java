package com.simon.world_banking_app_v1.service;

import com.simon.world_banking_app_v1.domain.dto.EmailDetails;
import com.simon.world_banking_app_v1.domain.entity.UserEntity;
import com.simon.world_banking_app_v1.payload.request.CreditAndDebitRequest;
import com.simon.world_banking_app_v1.payload.request.EnquiryRequest;
import com.simon.world_banking_app_v1.payload.request.TransferRequest;
import com.simon.world_banking_app_v1.payload.response.AccountInfo;
import com.simon.world_banking_app_v1.payload.response.BankResponse;
import com.simon.world_banking_app_v1.repository.UserEntityRepository;
import com.simon.world_banking_app_v1.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;

    private final EmailService emailService;
    @Override
    public BankResponse balanceEnquiry(EnquiryRequest request) {

        boolean isAccountExists = userEntityRepository.existsByAccountNumber(request.getAccountNumber());

        if(!isAccountExists){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NUMBER_NON_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NUMBER_NON_EXISTS_CODE)
                    .accountInfo(null)
                    .build();
        }

        UserEntity foundUser = userEntityRepository.findByAccountNumber(request.getAccountNumber());

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(foundUser.getAccountBalance())
                        .accountNumber(request.getAccountNumber())
                        .accountName(foundUser.getFirstName() + " " +
                                foundUser.getLastName() + " " +
                                foundUser.getOtherName())
                        .build())
                .build();

    }

    @Override
    public BankResponse creditAccount(CreditAndDebitRequest request) {

        //step 1 - check if the account number exists
        //step 2 - amount transfer should not be less than 0
        //step 3 - add amount to current balance and update

        boolean isAccountExists = userEntityRepository.existsByAccountNumber(request.getAccountNumber());

        if(!isAccountExists){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NUMBER_NON_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NUMBER_NON_EXISTS_CODE)
                    .accountInfo(null)
                    .build();
        }

        UserEntity userToCredit = userEntityRepository.findByAccountNumber(request.getAccountNumber());

        userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));

        //userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));

        userEntityRepository.save(userToCredit);

        EmailDetails creditAlert = EmailDetails.builder()
                .subject("CREDIT ALERT")
                .recipient(userToCredit.getEmail())
                .messageBody("Your account has been credited with " +
                        request.getAmount() + " from " + userToCredit.getFirstName() +
                        " your account balance is " + userToCredit.getAccountBalance())
                .build();

        emailService.sendEmailAlert(creditAlert);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREDITED_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREDITED_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(userToCredit.getFirstName() + " " + userToCredit.getLastName() + " " + userToCredit.getOtherName())
                        .accountBalance(userToCredit.getAccountBalance())
                        .accountNumber(request.getAccountNumber())
                        .build())
                .build();
    }

    @Override
    public BankResponse debitAccount(CreditAndDebitRequest request) {
        return null;
    }

    @Override
    public BankResponse transfer(TransferRequest request) {
        return null;
    }

    @Override
    public BankResponse nameEnquiry(EnquiryRequest request) {
        return null;
    }
}
