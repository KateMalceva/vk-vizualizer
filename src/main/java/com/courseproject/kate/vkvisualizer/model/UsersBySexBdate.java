package com.courseproject.kate.vkvisualizer.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UsersBySexBdate implements Serializable {
    public Response response;

    @Getter
    @Setter
    public static class Response{
        private List<Item> items;

        @Getter
        @Setter
        public static class Item{
            private Women women;
            private Men men;

            @Getter
            @Setter
            public static class Women{
                private int less_than_10 = 0;
                private int from_11_to_20 = 0;
                private int from_21_to_30 = 0;
                private int from_31_to_40 = 0;
                private int from_41_to_50 = 0;
                private int from_51_to_60 = 0;
                private int more_than_61 = 0;
            }
            @Getter
            @Setter
            public static class Men{
                private int less_than_10 = 0;
                private int from_11_to_20 = 0;
                private int from_21_to_30 = 0;
                private int from_31_to_40 = 0;
                private int from_41_to_50 = 0;
                private int from_51_to_60 = 0;
                private int more_than_61 = 0;
            }
        }
    }
}
