package com.simon.world_banking_app_v1.service;

import com.simon.world_banking_app_v1.payload.request.CreditAndDebitRequest;
import com.simon.world_banking_app_v1.payload.request.EnquiryRequest;
import com.simon.world_banking_app_v1.payload.request.TransferRequest;
import com.simon.world_banking_app_v1.payload.response.BankResponse;

public interface UserService {

    BankResponse balanceEnquiry(EnquiryRequest request);

    BankResponse creditAccount(CreditAndDebitRequest request);

    BankResponse debitAccount(CreditAndDebitRequest request);

    BankResponse transfer(TransferRequest request);

    BankResponse nameEnquiry(EnquiryRequest request);
}
