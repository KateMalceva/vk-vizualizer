package com.courseproject.kate.vkvisualizer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class UsersSearchResponse implements Serializable{
    @JsonProperty("response")
    private UsersInfo body;

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public static class UsersInfo implements Serializable {
        private Integer count;
        private List<User> items;

        @Getter
        @Setter
        @ToString
        @EqualsAndHashCode
        public static class User implements Serializable {
            @JsonProperty("id")
            private Integer id;
            @JsonProperty("first_name")
            private String firstName;
            @JsonProperty("last_name")
            private String lastName;
            @JsonProperty("can_access_closed")
            private Boolean canAccessClosed;
            @JsonProperty("is_closed")
            private Boolean isClosed;
            @JsonProperty("sex")
            private Integer sex;
            @JsonProperty("bdate")
            private String bdate;
            @JsonProperty("last_seen")
            private LastSeen lastSeen;
        }
        @Getter
        @Setter
        @ToString
        @Accessors(chain = true)
        public static class LastSeen implements Serializable {
            @JsonProperty("platform")
            private Integer platform;
            @JsonProperty("time")
            private Integer time;
        }
    }
}
