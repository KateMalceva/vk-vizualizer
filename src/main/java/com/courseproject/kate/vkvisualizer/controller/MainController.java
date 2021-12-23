package com.courseproject.kate.vkvisualizer.controller;

import com.courseproject.kate.vkvisualizer.dto.CitiesResponse;
import com.courseproject.kate.vkvisualizer.model.UserInfo;
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
    public List<UserInfo> getInfo(@RequestParam List<String> userIds, @RequestParam List<String> fields) {
        return userInfoService.getUserInfo(userIds, fields);
    }

    @GetMapping("regions")
    public Object getRegions(@RequestParam Integer countryId, @RequestParam Integer count) {
        return countryInfoService.getRegions(countryId, count);
    }

    @GetMapping("cities")
    public CitiesResponse getCities(@RequestParam Integer countyId, @RequestParam Integer regionId, @RequestParam Integer needAll, @RequestParam Integer count) {
        return countryInfoService.getCities(countyId, regionId, needAll, count);
    }
}
