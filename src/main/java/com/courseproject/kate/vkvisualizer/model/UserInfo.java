package com.courseproject.kate.vkvisualizer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserInfo {

    private Long id;
    private String firstName;
    private String lastName;
    private Boolean canAccessClosed;
    private Boolean isClosed;
    private LocalDate bDate;
    private Integer sex;
}
