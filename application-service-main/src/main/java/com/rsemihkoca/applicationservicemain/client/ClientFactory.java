package com.rsemihkoca.applicationservicemain.client;

import com.rsemihkoca.applicationservicemain.client.akbank.AkbankClient;
import com.rsemihkoca.applicationservicemain.client.garanti.GarantiClient;
//import com.rsemihkoca.applicationservicemain.client.userservice.UserClient;
//import com.rsemihkoca.applicationservicemain.client.userservice.UserServiceClient;
import com.rsemihkoca.applicationservicemain.dto.request.ApplicationRequest;
import com.rsemihkoca.applicationservicemain.dto.request.BankApplicationRequest;
import com.rsemihkoca.applicationservicemain.enums.BankType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class ClientFactory {
    private final GarantiClient garantiClient;
    private final AkbankClient akbankClient;

    public ClientFactory(GarantiClient garantiClient, AkbankClient akbankClient) {
        this.garantiClient = garantiClient;
        this.akbankClient = akbankClient;
    }

//    private void sendApplication(ApplicationRequest request, User user) {
//        BankType bankType = BankType.valueOf(request.getBankName().toUpperCase());
//        BankServiceClient bankServiceClient = clientFactory.createBankClient(bankType);
//        BankApplicationRequest bankApplicationRequest = new BankApplicationRequest();
//        bankApplicationRequest.setUserId(user.getUserId());
//        bankServiceClient.createApplication(bankApplicationRequest);
//    }

    public BankServiceClient createBankClient(BankType bankName) {
        return switch (bankName) {
            case GARANTI -> garantiClient;
            case AKBANK -> akbankClient;
        };
    }

}