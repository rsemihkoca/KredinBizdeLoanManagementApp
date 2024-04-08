package com.rsemihkoca.applicationservicemain.client;


import com.rsemihkoca.applicationservicemain.dto.request.BankApplicationRequest;
import com.rsemihkoca.applicationservicemain.dto.response.ApplicationResponse;

public interface BankServiceClient {
    ApplicationResponse createApplication(BankApplicationRequest request);
}
