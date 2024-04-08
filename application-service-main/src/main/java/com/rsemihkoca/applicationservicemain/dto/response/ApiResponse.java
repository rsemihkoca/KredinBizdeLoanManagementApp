package com.rsemihkoca.applicationservicemain.dto.response;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

}
