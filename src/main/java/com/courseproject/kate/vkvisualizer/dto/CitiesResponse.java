package com.courseproject.kate.vkvisualizer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class CitiesResponse implements Serializable {

    @JsonProperty("response")
    private Cities body;

    @Getter
    @Setter
    @ToString
    public static class Cities {
        private Integer count;
        private List<City> items;

        @Getter
        @Setter
        @ToString
        public static class City {
            private int id;
            private String title;
            private String area;
            private String region;
        }
    }
}
