package com.patika.kredinbizdeservice.client;

import com.patika.kredinbizdeservice.client.garanti.GarantiClient;
import com.patika.kredinbizdeservice.client.akbank.AkbankClient;
import com.patika.kredinbizdeservice.enums.BankType;
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