package com.courseproject.kate.vkvisualizer.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.management.PlatformLoggingMXBean;
import java.util.List;

@Getter
@Setter
public class UsersByLastSeen implements Serializable {
    private Response response;

    @Getter
    @Setter
    public static class Response {
        private List<Item> items;

        @Getter
        @Setter
        public static class Item {
            private Platform platform;

            @Getter
            @Setter
            public static class Platform {
                private int mobile_version = 0;
                private int iPhone_app = 0;
                private int iPad_app = 0;
                private int android_app = 0;
                private int windows_Phone_app = 0;
                private int application_for_Windows_10 = 0;
                private int full_version_of_the_site = 0;
            }
        }
    }
}
