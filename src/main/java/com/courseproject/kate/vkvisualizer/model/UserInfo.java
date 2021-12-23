package com.courseproject.kate.vkvisualizer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
public class UserInfo {

    private Long id;
    private String firstName;
    private String lastName;
    private Boolean canAccessClosed;
    private Boolean isClosed;
    private LocalDate bDate;
}
