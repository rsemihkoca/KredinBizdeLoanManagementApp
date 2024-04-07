package com.patika.akbankservice.entity;

import com.patika.akbankservice.enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Application {

    private Long userId;
    private LocalDateTime createDate;
    private ApplicationStatus applicationStatus;
}
