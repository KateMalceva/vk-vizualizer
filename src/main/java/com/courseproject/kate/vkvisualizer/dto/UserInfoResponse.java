package com.courseproject.kate.vkvisualizer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserInfoResponse implements Serializable {

    @JsonProperty("response")
    private List<UserInfo> body;

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public static class UserInfo implements Serializable {

        @JsonProperty("id")
        private Long id;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        @JsonProperty("can_access_closed")
        private Boolean canAccessClosed;
        @JsonProperty("is_closed")
        private Boolean isClosed;
        @JsonProperty("bdate")
        private String bDate;
    }
}
