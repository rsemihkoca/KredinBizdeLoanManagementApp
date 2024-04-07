package com.patika.kredinbizdeservice.client;

import com.patika.kredinbizdeservice.client.dto.request.BankApplicationRequest;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;

public interface BankServiceClient {
    ApplicationResponse createApplication(BankApplicationRequest request);
}
