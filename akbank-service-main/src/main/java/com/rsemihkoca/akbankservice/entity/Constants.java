package com.rsemihkoca.akbankservice.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LoanTable {
        public static final String TABLE_NAME = "AKBANK_LOAN";
        public static final String ID = "ID";
        public static final String AMOUNT = "AMOUNT";
        public static final String DURATION = "DURATION";
        public static final String INTEREST_RATE = "INTEREST_RATE";
        public static final String CREATE_DATE = "CREATE_DATE";
        public static final String UPDATE_DATE = "UPDATE_DATE";
        public static final String TYPE = "TYPE";

    }
}
