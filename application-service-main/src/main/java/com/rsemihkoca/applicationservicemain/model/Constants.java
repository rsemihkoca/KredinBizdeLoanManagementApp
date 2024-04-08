package com.rsemihkoca.applicationservicemain.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class userTable {
        public static final String TABLE_NAME = "USER";
        public static final String USER_ID = "USER_ID";
        public static final String NAME = "NAME";
        public static final String EMAIL = "EMAIL";
        public static final String AGE = "AGE";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class addressTable {
        public static final String TABLE_NAME = "ADDRESS";
        public static final String ADDRESS_ID = "ADDRESS_ID";
        public static final String ADDRESS_TITLE = "ADDRESS_TITLE";
        public static final String ADDRESS_DESCRIPTION = "ADDRESS_DESCRIPTION";
    }
}
