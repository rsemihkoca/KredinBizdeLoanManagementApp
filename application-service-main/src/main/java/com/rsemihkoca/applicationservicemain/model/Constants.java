package com.rsemihkoca.applicationservicemain.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class applicationTable {
        public static final String TABLE_NAME = "APPLICATION";
        public static final String APPLICATION_ID = "APPLICATION_ID";
        public static final String BANK_NAME = "BANK_NAME";
        public static final String IS_ACTIVE = "IS_ACTIVE";
        public static final String APPLICATION_DATE = "APPLICATION_DATE";
        public static final String APPLICATION_STATUS = "APPLICATION_STATUS";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class loanTable {
        public static final String LOAN_ID = "LOAN_ID";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class userTable {
        public static final String EMAIL = "EMAIL";
    }


}
