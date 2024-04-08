package com.rsemihkoca.applicationservicemain.client;

import com.rsemihkoca.applicationservicemain.client.akbank.AkbankClient;
import com.rsemihkoca.applicationservicemain.client.garanti.GarantiClient;
import com.rsemihkoca.applicationservicemain.enums.BankType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class BankClientFactory {
    private final GarantiClient garantiClient;
    private final AkbankClient akbankClient;

    public BankClientFactory(GarantiClient garantiClient, AkbankClient akbankClient) {
        this.garantiClient = garantiClient;
        this.akbankClient = akbankClient;
    }

    public BankServiceClient createBankClient(BankType bankName) {
        return switch (bankName) {
            case GARANTI -> garantiClient;
            case AKBANK -> akbankClient;
        };
    }
}