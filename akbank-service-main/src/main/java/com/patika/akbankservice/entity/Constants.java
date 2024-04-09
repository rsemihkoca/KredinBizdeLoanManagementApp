package com.patika.akbankservice.entity;

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
        public static final String USER_ID = "USER_ID";
        public static final String CREATE_DATE = "CREATE_DATE";
        public static final String APPLICATION_STATUS = "APPLICATION_STATUS";
    }
}
