package com.rsemihkoca.applicationservicemain.dto.response;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse implements java.io.Serializable{

    private String userEmail;
    private String applicationDate;
    private String applicationStatus;

}



