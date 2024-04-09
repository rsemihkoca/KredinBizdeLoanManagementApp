package com.rsemihkoca.applicationservicemain.client;

import com.rsemihkoca.applicationservicemain.client.akbank.AkbankClient;
import com.rsemihkoca.applicationservicemain.client.garanti.GarantiClient;
import com.rsemihkoca.applicationservicemain.client.userservice.UserClient;
import com.rsemihkoca.applicationservicemain.client.userservice.UserServiceClient;
import com.rsemihkoca.applicationservicemain.enums.BankType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class ClientFactory {
    private final GarantiClient garantiClient;
    private final AkbankClient akbankClient;
    private final UserClient userClient;

    public ClientFactory(GarantiClient garantiClient, AkbankClient akbankClient, UserClient userClient) {
        this.garantiClient = garantiClient;
        this.akbankClient = akbankClient;
        this.userClient = userClient;
    }

    public BankServiceClient createBankClient(BankType bankName) {
        return switch (bankName) {
            case GARANTI -> garantiClient;
            case AKBANK -> akbankClient;
        };
    }

    public UserClient createUserServiceClient() {
        return userClient;
    }
}