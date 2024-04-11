package com.rsemihkoca.bankservicemain.client;

import com.rsemihkoca.bankservicemain.client.akbank.AkbankServiceClient;
import com.rsemihkoca.bankservicemain.client.garanti.GarantiServiceClient;
import com.rsemihkoca.bankservicemain.enums.BankType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
@AllArgsConstructor
public class ClientFactory{
    private final GarantiServiceClient garantiClient;
    private final AkbankServiceClient akbankClient;


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