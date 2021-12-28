package com.courseproject.kate.vkvisualizer.service.impl;

import com.courseproject.kate.vkvisualizer.config.VkVisualizerConfig;
import com.courseproject.kate.vkvisualizer.dto.RegionsResponse;
import com.courseproject.kate.vkvisualizer.dto.UserInfoResponse;
import com.courseproject.kate.vkvisualizer.dto.UsersSearchResponse;
import com.courseproject.kate.vkvisualizer.model.UserInfo;
import com.courseproject.kate.vkvisualizer.model.UsersByLastSeen;
import com.courseproject.kate.vkvisualizer.model.UsersBySexBdate;
import com.courseproject.kate.vkvisualizer.model.UsersSearchInfo;
import com.courseproject.kate.vkvisualizer.service.UserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private static final String USERS_GET = "/users.get?user_ids={user_ids}&fields={fields}&access_token={token}&v={v}&lang={lang}";
    private static final String USERS_BY_SEX_AND_BDATE_GET = "/users.search?count={count}&fields={fields}&city={city}&access_token={token}&v={v}";
    private static final String USERS_BY_LAST_SEEN_GET = "/users.search?count={count}&fields={fields}&city={city}&access_token={token}&v={v}";

    private final RestTemplate template;
    private final VkVisualizerConfig config;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

    @Override
    public List<UserInfo> getUserInfo(List<String> userIds, List<String> fields, Integer lang) {
        final Map<String, Serializable> params = new HashMap<>() {{
            put("user_ids", String.join(",", userIds));
            put("fields", String.join(",", fields));
            put("token", config.getToken());
            put("v", config.getVersion());
            put("lang", lang);
        }};
        final UserInfoResponse res = template.getForObject(USERS_GET, UserInfoResponse.class, params);
        if (res == null) {
            return null;
        }
        return res.getBody().stream()
                .filter(i -> i.getBDate() != null && i.getBDate().split("\\.").length == 3)
                .map(this::userMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsersBySexBdate.Response.Item> getUsersBySexBdate(
            Integer count,
            List<String> fields,
            Integer city
    ) {
       final Map<String, Serializable> usersParams = new HashMap<>() {{
            put("count", count);
            put("fields", String.join(",", fields));
            put("city", city);
            put("token", config.getAccess_token());
            put("v", config.getVersion());
        }};

        final UsersSearchResponse result = template.getForObject(USERS_BY_SEX_AND_BDATE_GET, UsersSearchResponse.class, usersParams);
        if (result == null) {
            return null;
        }

        List<UsersSearchInfo> users = result.getBody().getItems().stream()
                .filter(i -> i.getBdate() != null && i.getBdate().split("\\.").length == 3)
                .map(this::usersSearchMapper)
                .collect(Collectors.toList());

        List<UsersBySexBdate.Response.Item> list = new LinkedList<>();

        UsersBySexBdate.Response.Item.Women women = new UsersBySexBdate.Response.Item.Women();
        UsersBySexBdate.Response.Item.Men men = new UsersBySexBdate.Response.Item.Men();
        for(UsersSearchInfo usersSearchInfo : users) {
            ageCalculator(usersSearchInfo, women, men);
        }
        UsersBySexBdate.Response.Item item = new UsersBySexBdate.Response.Item();
        item.setWomen(women);
        item.setMen(men);
        list.add(item);
        return list;
    }

    private void ageCalculator (UsersSearchInfo usersSearchInfo, UsersBySexBdate.Response.Item.Women women, UsersBySexBdate.Response.Item.Men men){
        int i;
        int age = Period.between(usersSearchInfo.getBdate(), LocalDate.now()).getYears();
        if (usersSearchInfo.getSex() == 1) {
            if (age <= 10) {
                i = women.getLess_than_10();
                women.setLess_than_10(i+1);
            } else if (age >= 11 && age <= 20) {
                i = women.getFrom_11_to_20();
                women.setFrom_11_to_20(i+1);
            } else if (age >= 21 && age <= 30) {
                i = women.getFrom_21_to_30();
                women.setFrom_21_to_30(i+1);
            } else if (age >= 31 && age <= 40) {
                i = women.getFrom_31_to_40();
                women.setFrom_31_to_40(i+1);
            } else if (age >= 41 && age <= 50) {
                i = women.getFrom_41_to_50();
                women.setFrom_41_to_50(i+1);
            } else if (age >= 51 && age <= 60) {
                i = women.getFrom_51_to_60();
                women.setFrom_51_to_60(i+1);
            } else if (age >= 61) {
                i = women.getMore_than_61();
                women.setMore_than_61(i+1);
            }
        }
        else {
            if (age <= 10) {
                i = men.getLess_than_10();
                men.setLess_than_10(i+1);
            } else if (age >= 11 && age <= 20) {
                i = men.getFrom_11_to_20();
                men.setFrom_11_to_20(i+1);
            } else if (age >= 21 && age <= 30) {
                i = men.getFrom_21_to_30();
                men.setFrom_21_to_30(i+1);
            } else if (age >= 31 && age <= 40) {
                i = men.getFrom_31_to_40();
                men.setFrom_31_to_40(i+1);
            } else if (age >= 41 && age <= 50) {
                i = men.getFrom_41_to_50();
                men.setFrom_41_to_50(i+1);
            } else if (age >= 51 && age <= 60) {
                i = men.getFrom_51_to_60();
                men.setFrom_51_to_60(i+1);
            } else if (age >= 61) {
                i = men.getMore_than_61();
                men.setMore_than_61(i+1);
            }
        }
    }

    @Override
    public List<UsersByLastSeen.Response.Item> getUsersByLastSeen(
            Integer count,
            List<String> fields,
            Integer city
    ) {
        final Map<String, Serializable> params = new HashMap<>() {{
            put("count", count);
            put("fields", String.join(",", fields));
            put("city", city);
            put("token", config.getAccess_token());
            put("v", config.getVersion());
        }};
        final UsersSearchResponse result = template.getForObject(USERS_BY_LAST_SEEN_GET, UsersSearchResponse.class, params);
        if (result == null) {
            return null;
        }

        List<UsersSearchInfo> users = result.getBody().getItems().stream()
                .map(this::usersSearchLastSeenMapper)
                .collect(Collectors.toList());
        List<UsersByLastSeen.Response.Item> list = new LinkedList<>();

        UsersByLastSeen.Response.Item.Platform platform = new UsersByLastSeen.Response.Item.Platform();
        for(UsersSearchInfo usersSearchInfo : users) {
            platformCalculator(usersSearchInfo, platform);
        }
        UsersByLastSeen.Response.Item item = new UsersByLastSeen.Response.Item();
        item.setPlatform(platform);
        list.add(item);
        return list;
    }

    private void platformCalculator (UsersSearchInfo usersSearchInfo, UsersByLastSeen.Response.Item.Platform platform) {
        int i;
        if (usersSearchInfo.getLastSeen().getPlatform() == 1) {
            i = platform.getMobile_version();
            platform.setMobile_version(i + 1);
        } else if (usersSearchInfo.getLastSeen().getPlatform() == 2) {
            i = platform.getIPhone_app();
            platform.setIPhone_app(i + 1);
        } else if (usersSearchInfo.getLastSeen().getPlatform() == 3) {
            i = platform.getIPad_app();
            platform.setIPad_app(i + 1);
        } else if (usersSearchInfo.getLastSeen().getPlatform() == 4) {
            i = platform.getAndroid_app();
            platform.setAndroid_app(i + 1);
        } else if (usersSearchInfo.getLastSeen().getPlatform() == 5) {
            i = platform.getWindows_Phone_app();
            platform.setWindows_Phone_app(i + 1);
        } else if (usersSearchInfo.getLastSeen().getPlatform() == 6) {
            i = platform.getApplication_for_Windows_10();
            platform.setApplication_for_Windows_10(i + 1);
        } else {
            i = platform.getFull_version_of_the_site();
            platform.setFull_version_of_the_site(i + 1);
        }
    }

    private UserInfo userMapper(UserInfoResponse.UserInfo userInfo) {
        return new UserInfo()
                .setId(userInfo.getId())
                .setFirstName(userInfo.getFirstName())
                .setLastName(userInfo.getLastName())
                .setCanAccessClosed(userInfo.getCanAccessClosed())
                .setIsClosed(userInfo.getIsClosed())
                .setBDate(LocalDate.from(formatter.parse(userInfo.getBDate())))
                .setSex(userInfo.getSex());
    }

    private UsersSearchInfo usersSearchMapper(UsersSearchResponse.UsersInfo.User usersInfo) {
        return new UsersSearchInfo()
                .setId(usersInfo.getId())
                .setFirstName(usersInfo.getFirstName())
                .setLastName(usersInfo.getLastName())
                .setCanAccessClosed(usersInfo.getCanAccessClosed())
                .setIsClosed(usersInfo.getIsClosed())
                .setSex(usersInfo.getSex())
                .setBdate(LocalDate.from(formatter.parse(usersInfo.getBdate())));
    }

    private UsersSearchInfo usersSearchLastSeenMapper(UsersSearchResponse.UsersInfo.User usersInfo) {
        return new UsersSearchInfo()
                .setId(usersInfo.getId())
                .setFirstName(usersInfo.getFirstName())
                .setLastName(usersInfo.getLastName())
                .setCanAccessClosed(usersInfo.getCanAccessClosed())
                .setIsClosed(usersInfo.getIsClosed())
                .setSex(usersInfo.getSex())
                //.setBdate(LocalDate.from(formatter.parse(usersInfo.getBdate())))
                .setLastSeen(new UsersSearchInfo.LastSeen().setPlatform(usersInfo.getLastSeen().getPlatform()).setTime(usersInfo.getLastSeen().getTime()));
    }
}
