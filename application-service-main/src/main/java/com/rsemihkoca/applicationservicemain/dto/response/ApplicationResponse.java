package com.rsemihkoca.applicationservicemain.dto.response;

import com.rsemihkoca.applicationservicemain.enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
public class ApplicationResponse implements java.io.Serializable{

    private Long userId;
    private LocalDateTime createDate;
    private ApplicationStatus applicationStatus;
}
