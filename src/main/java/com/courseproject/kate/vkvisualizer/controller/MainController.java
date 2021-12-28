package com.courseproject.kate.vkvisualizer.controller;

import com.courseproject.kate.vkvisualizer.dto.CitiesResponse;
import com.courseproject.kate.vkvisualizer.dto.RegionsResponse;
import com.courseproject.kate.vkvisualizer.dto.UserInfoResponse;
import com.courseproject.kate.vkvisualizer.dto.UsersSearchResponse;
import com.courseproject.kate.vkvisualizer.model.UserInfo;
import com.courseproject.kate.vkvisualizer.model.UsersByLastSeen;
import com.courseproject.kate.vkvisualizer.model.UsersBySexBdate;
import com.courseproject.kate.vkvisualizer.model.UsersSearchInfo;
import com.courseproject.kate.vkvisualizer.service.CountryInfoService;
import com.courseproject.kate.vkvisualizer.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/visualizer/v1/")
public class MainController {

    private final CountryInfoService countryInfoService;
    private final UserInfoService userInfoService;

    @GetMapping("user_info")
    public List<UserInfo> getInfo(@RequestParam List<String> userIds, @RequestParam List<String> fields, @RequestParam(required = false, defaultValue = "0") Integer lang) {
        return userInfoService.getUserInfo(userIds, fields, lang);
    }

    @GetMapping("regions")
    public RegionsResponse getRegions(
            @RequestParam Integer countryId,
            @RequestParam Integer count,
            @RequestParam(required = false, defaultValue = "0") Integer lang
    ) {
        return countryInfoService.getRegions(countryId, count, lang);
    }

    @GetMapping("cities")
    public CitiesResponse getCities(
            @RequestParam Integer countyId,
            @RequestParam Integer regionId,
            @RequestParam Integer needAll,
            @RequestParam Integer count,
            @RequestParam(required = false, defaultValue = "0") Integer lang
    ) {
        return countryInfoService.getCities(countyId, regionId, needAll, count, lang);
    }

    @GetMapping("users_by_sex_and_bdate")
    public List<UsersBySexBdate.Response.Item> getUsersBySexBdate(
            @RequestParam Integer count,
            @RequestParam List<String> fields,
            @RequestParam Integer city
    ) {
        return userInfoService.getUsersBySexBdate(count, fields, city);
    }

    @GetMapping("users_by_last_seen")
    public List<UsersByLastSeen.Response.Item> getUsersByLastSeen(
            @RequestParam Integer count,
            @RequestParam List<String> fields,
            @RequestParam Integer city
    ) {
        return userInfoService.getUsersByLastSeen(count, fields, city);
    }
}
