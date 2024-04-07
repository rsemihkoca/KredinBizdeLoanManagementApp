package com.patika.garantiservice.entity;

import com.patika.garantiservice.enums.ApplicationStatus;
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
