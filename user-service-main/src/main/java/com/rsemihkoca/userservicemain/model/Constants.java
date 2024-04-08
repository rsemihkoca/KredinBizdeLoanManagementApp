package com.rsemihkoca.userservicemain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public final class Constants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class userTable {
        public static final String NAME = "name";
        public static final String SURNAME = "surname";
        public static final String BIRTH_DATE = "birth_date";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String IS_ACTIVE = "is_active";
        public static final String ADDRESS = "address_id";
    }
}
