package com.simon.world_banking_app_v1.service;

import com.simon.world_banking_app_v1.payload.request.UserRequest;
import com.simon.world_banking_app_v1.payload.response.BankResponse;

public interface AuthService {

    BankResponse registerUser(UserRequest userRequest);
}
