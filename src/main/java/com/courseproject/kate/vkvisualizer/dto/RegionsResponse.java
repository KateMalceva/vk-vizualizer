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
@ToString
public class RegionsResponse implements Serializable {

    @JsonProperty("response")
    private Regions body;

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public static class Regions implements Serializable {
        @JsonProperty("count")
        private Integer count;
        private List<Region> items;

        @Getter
        @Setter
        @ToString
        @EqualsAndHashCode
        public static class Region implements Serializable {
            @JsonProperty("id")
            private Integer id;
            @JsonProperty("title")
            private String title;
        }

    }

}
