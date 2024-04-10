package com.rsemihkoca.bankservicemain.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    public static final String LOAN_CACHE = "LOAN_CACHE";
}