package com.courseproject.kate.vkvisualizer.service;

import com.courseproject.kate.vkvisualizer.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    List<UserInfo> getUserInfo(List<String> userIds, List<String> fields);
}
