package com.rsemihkoca.userservicemain.model;


import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    private String userId;
    private String name;
    private Integer age;

}
