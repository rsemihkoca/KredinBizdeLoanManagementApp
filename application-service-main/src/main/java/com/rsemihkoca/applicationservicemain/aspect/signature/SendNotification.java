package com.rsemihkoca.applicationservicemain.aspect.signature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import com.rsemihkoca.applicationservicemain.enums.NotificationContent;
import jakarta.validation.constraints.NotNull;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SendNotification{

    @NotNull
    NotificationContent content() ;
}