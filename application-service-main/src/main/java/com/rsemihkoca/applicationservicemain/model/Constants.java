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
        public static final String IS_ACTIVE = "IS_ACTIVE";
        public static final String APPLICATION_STATUS = "APPLICATION_STATUS";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class bankTable {
        public static final String TABLE_NAME = "BANK";
        public static final String BANK_ID = "BANK_ID";
        public static final String NAME = "NAME";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class loanTable {
        public static final String TABLE_NAME = "LOAN";
        public static final String LOAN_ID = "LOAN_ID";
        public static final String AMOUNT = "AMOUNT";
        public static final String LOAN_TYPE = "LOAN_TYPE";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class userTable {
        public static final String TABLE_NAME = "USER";
        public static final String USER_ID = "USER_ID";
        public static final String EMAIL = "EMAIL";
    }


}
