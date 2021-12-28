package com.courseproject.kate.vkvisualizer.model;

import com.courseproject.kate.vkvisualizer.dto.UsersSearchResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UsersSearchInfo {
    private Integer id;
    private String firstName;
    private String lastName;
    private Boolean canAccessClosed;
    private Boolean isClosed;
    private Integer sex;
    private LocalDate bdate;
    private LastSeen lastSeen;

    @Getter
    @Setter
    @ToString
    public static class LastSeen{
        public Integer platform;
        public Integer time;
    }
}
