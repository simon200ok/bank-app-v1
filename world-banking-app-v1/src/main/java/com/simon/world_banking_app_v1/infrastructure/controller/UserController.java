package com.simon.world_banking_app_v1.infrastructure.controller;

import com.simon.world_banking_app_v1.payload.request.CreditAndDebitRequest;
import com.simon.world_banking_app_v1.payload.request.EnquiryRequest;
import com.simon.world_banking_app_v1.payload.response.BankResponse;
import com.simon.world_banking_app_v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/balance-enquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest request){
        return userService.balanceEnquiry(request);
    }

    @PostMapping("/credit-account")
    public BankResponse creditAccount(@RequestBody CreditAndDebitRequest request){
        return userService.creditAccount(request);
    }

}
