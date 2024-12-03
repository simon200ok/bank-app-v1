package com.simon.world_banking_app_v1.service;

import com.simon.world_banking_app_v1.domain.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
